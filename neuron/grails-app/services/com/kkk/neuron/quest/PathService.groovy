package com.kkk.neuron.quest

import com.kkk.neuron.NeuronException
import com.kkk.neuron.auth.User

class PathService {
	
	def mailService
	def backgroundService

    def deliver(Path parentPath, Long nextUserId) {
		def nextUser = User.get(nextUserId)
		if (!nextUser) {
			return
		}
		
		def newPath = new Path(parent: parentPath, quest: parentPath.quest, user: nextUser)
		if (!newPath.save(flush: true)) {
			path.errors.each {
				println it
			}
			throw new NeuronException("Could not create a Path");
		}
    }
	
    def deliver(Path parentPath, String[] nextUserIds) {
    	nextUserIds.each {
			deliver(parentPath, it as Long)
    	}
    }
	
	def deliverToEmail(parentPath, email) {
		def nextDelivery = new Delivery(parentPath: parentPath, email: email)
		if (!nextDelivery.save(flush:true)) {
			throw new NeuronException("Could not deliver to email");
		}
		
		backgroundService.execute("Send mail") {
			mailService.sendMail {
				to email
				from "lab@nextree.co.kr"
				subject "You've got a quest"
				body (view: "/emailconfirmation/confirmationRequest",
						model: [parentPath: parentPath, email: email]
						)
			}
		}
		
	}
}
