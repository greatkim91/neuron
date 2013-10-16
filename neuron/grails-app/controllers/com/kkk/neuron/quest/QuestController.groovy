package com.kkk.neuron.quest

import grails.plugin.springsecurity.annotation.Secured;

import org.springframework.dao.DataIntegrityViolationException

import com.kkk.neuron.auth.User;

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class QuestController {

	def springSecurityService
	def rewardService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
    def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def currentUser = springSecurityService.currentUser
		def w = Quest.where {
			owner == currentUser
		}
		
		[questInstanceList: w.list(params), questInstanceTotal: w.count()]
    }

    def create() {
        [questInstance: new Quest(params)]
    }

    def save() {
		params.owner = springSecurityService.currentUser
        def questInstance = new Quest(params)
        if (!questInstance.save(flush: true)) {
            render(view: "create", model: [questInstance: questInstance])
            return
        }
		
		rewardService.withdraw(
			springSecurityService.currentUser,
			params.reward?.toLong(), 
			"Withdraw for Quest # " + questInstance.id)

        flash.message = message(code: 'default.created.message', args: [message(code: 'quest.label', default: 'Quest'), questInstance.id])
        redirect(action: "show", id: questInstance.id)
    }

    def show(Long id) {
        def questInstance = Quest.get(id)
        if (!questInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quest.label', default: 'Quest'), id])
            redirect(action: "list")
            return
        }
		
		def pathInstances = Path.findAllByQuest(questInstance)
		def relationshipInstances = Relationship.findAllByOwner(springSecurityService.currentUser)
		
        [questInstance: questInstance, pathInstances: pathInstances, relationshipInstances: relationshipInstances]
    }

    def edit(Long id) {
        def questInstance = Quest.get(id)
        if (!questInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quest.label', default: 'Quest'), id])
            redirect(action: "list")
            return
        }

        [questInstance: questInstance]
    }

    def update(Long id, Long version) {
        def questInstance = Quest.get(id)
        if (!questInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quest.label', default: 'Quest'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (questInstance.version > version) {
                questInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'quest.label', default: 'Quest')] as Object[],
                          "Another user has updated this Quest while you were editing")
                render(view: "edit", model: [questInstance: questInstance])
                return
            }
        }

        questInstance.properties = params

        if (!questInstance.save(flush: true)) {
            render(view: "edit", model: [questInstance: questInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'quest.label', default: 'Quest'), questInstance.id])
        redirect(action: "show", id: questInstance.id)
    }

    def delete(Long id) {
        def questInstance = Quest.get(id)
        if (!questInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quest.label', default: 'Quest'), id])
            redirect(action: "list")
            return
        }

        try {
            questInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'quest.label', default: 'Quest'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quest.label', default: 'Quest'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def deliver() {
		def quest = Quest.get(params.id)
		
		if (!quest) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'quest.label', default: 'Quest'), id])
			redirect(action: "show", id: params.id)
			return
		}
		
		params.next_user_id.each {
			def nextUser = User.get(it)
			if (!nextUser) {
				return
			}
			
			def newPath = new Path(quest: quest, user: nextUser)
			if (!newPath.save(flush: true)) {
				path.errors.each {
					println it
				}
				render 'error'
				return
			}
		}
		
		
		flash.message = "The quest is delivered";
				
		redirect(action: "show", id: quest.id)
	}
	
	def choose() {
		def path = Path.get(params.path_id)
		
		if (!path) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'path.label', default: 'Path'), id])
			redirect(action: "show", id: params.id)
			return
		}
		
		
		path.choose(path.quest.reward, true)
		
		// depoist
		def pathInstances = Path.findAllByQuest(path.quest);
		pathInstances.each {
			if (it.reward == 0) return
			
			rewardService.depoist(
				it.user,
				it.reward,
				"Depoist for Quest # " + it.quest.id)
		}
		
		
		flash.message = "The answer is chosen";
		
		redirect(action: "show", id: params.id)
	}

}
