package com.kkk.neuron.quest

import com.kkk.neuron.auth.User;

import grails.plugin.springsecurity.annotation.Secured;

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class PathController {
	
	def springSecurityService

	static allowedMethods = [save: "POST", deliver: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
	
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def pathUser = springSecurityService.currentUser
		def w = Path.where {
			user == pathUser
		}
		
		[pathInstanceList: w.list(params), pathInstanceTotal: w.count()]
	}
	
	def show(Long id) {
		def pathInstance = Path.get(id)
		if (!pathInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'path.label', default: 'Path'), id])
			redirect(action: "list")
			return
		}
		
		def pathInstances = Path.findAllByQuest(pathInstance.quest)

		[pathInstance: pathInstance, questInstance: pathInstance.quest, pathInstances: pathInstances]
	}
	
    def answer() { 
		def path = Path.get(params.id)
		
		if (!path) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'path.label', default: 'Path'), id])
			redirect(action: "show", id: params.id)
			return
		}
		
		path.answer = params.answer
		if (!path.save(flush: true)) {
			path.errors.each {
				println it
			}
			render 'error'
			return
		}
		
		redirect(action: "show", id: path.id)
	}
	
	def deliver() {
		def path = Path.get(params.id)
		
		if (!path) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'path.label', default: 'Path'), id])
			redirect(action: "show", id: params.id)
			return
		}
		
		def nextUser = User.findByUsername(params.next_username)
		if (!nextUser) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.next_username])
			redirect(action: "show", id: params.id)
			return
		}
		
		def newPath = new Path(parent: path, quest: path.quest, user: nextUser)
		
		if (!newPath.save(flush: true)) {
			path.errors.each {
				println it
			}
			render 'error'
			return
		}
		
		flash.message = "The quest is delivered";
				
		redirect(action: "show", id: path.id)
	}
	
}
