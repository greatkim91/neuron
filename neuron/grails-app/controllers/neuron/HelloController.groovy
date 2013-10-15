package neuron

import grails.plugin.springsecurity.annotation.Secured;

class HelloController {
	
	def springSecurityService

	@Secured(['ROLE_ADMIN'])
    def index() { 
		render("hello world!" + springSecurityService.currentUser.username);
	}
}
