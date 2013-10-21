package com.kkk.neuron.quest

import grails.plugin.springsecurity.annotation.Secured

import com.kkk.neuron.auth.User

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class ConfirmationController {
	
	def pathService
	def deliveryService
	
	static allowedMethods = [register: "POST"]

    def certi() {
		render(view: 'certi', model:[newUser: new User()])
	}
	
	def register() {
		
		def newUser = new User(params)
		
		deliveryService.register(newUser, params.path_id, params.email)
		
		if (newUser.hasErrors()) {
			render(view: "register", model: [newUser: newUser])
			return
		}
		
		def parentPath = Path.get(params.path_id)
		def newPath = Path.findByParentAndUser(parentPath, newUser)
		
		redirect(action: "show", id: newUser.id, params: [path_id: newPath.id])
	}
	
	def show(Long id) {
		def newUser = User.get(id)
		if (!newUser) {
			flash.message = 'Not found user: ' + id
		}

		[newUser: newUser, path: Path.get(params.path_id)]
	}
}
