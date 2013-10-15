package com.kkk.neuron.quest

import grails.plugin.springsecurity.annotation.Secured;

import org.springframework.dao.DataIntegrityViolationException

import com.kkk.neuron.auth.User;

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class QuestionController {

	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
    def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def owner = springSecurityService.currentUser
		def w = Question.where {
			questioner == owner
		}
		
		[questionInstanceList: w.list(params), questionInstanceTotal: w.count()]
    }

    def create() {
        [questionInstance: new Question(params)]
    }

    def save() {
		params.questioner = springSecurityService.currentUser
        def questionInstance = new Question(params)
        if (!questionInstance.save(flush: true)) {
            render(view: "create", model: [questionInstance: questionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def show(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }
		
		def pathInstances = Path.findAllByQuestion(questionInstance);

        [questionInstance: questionInstance, pathInstances: pathInstances]
    }

    def edit(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        [questionInstance: questionInstance]
    }

    def update(Long id, Long version) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (questionInstance.version > version) {
                questionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'question.label', default: 'Question')] as Object[],
                          "Another user has updated this Question while you were editing")
                render(view: "edit", model: [questionInstance: questionInstance])
                return
            }
        }

        questionInstance.properties = params

        if (!questionInstance.save(flush: true)) {
            render(view: "edit", model: [questionInstance: questionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'question.label', default: 'Question'), questionInstance.id])
        redirect(action: "show", id: questionInstance.id)
    }

    def delete(Long id) {
        def questionInstance = Question.get(id)
        if (!questionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
            return
        }

        try {
            questionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'question.label', default: 'Question'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def deliver() {
		def question = Question.get(params.id)
		
		if (!question) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'question.label', default: 'Question'), id])
			redirect(action: "show", id: params.id)
			return
		}
		
		def nextUser = User.findByUsername(params.next_username)
		if (!nextUser) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.next_username])
			redirect(action: "show", id: params.id)
			return
		}
		
		def newPath = new Path(question: question, user: nextUser)
		
		if (!newPath.save(flush: true)) {
			path.errors.each {
				println it
			}
			render 'error'
			return
		}
		
		flash.message = "The question is delivered";
				
		redirect(action: "show", id: question.id)
	}
	
	def choose() {
		def path = Path.get(params.path_id)
		
		if (!path) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'path.label', default: 'Path'), id])
			redirect(action: "show", id: params.id)
			return
		}
		
		
		path.choose(path.question.reward, true)
		flash.message = "The answer is chosen";
		
		redirect(action: "show", id: params.id)
	}

}
