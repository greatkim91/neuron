package neuron

import grails.plugin.springsecurity.annotation.Secured;

@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
class HelloController {
	
	def springSecurityService

    def index() { 
		render("hello world!" + springSecurityService.currentUser?.username);
	}
	
	def info() {
		
	}
}
