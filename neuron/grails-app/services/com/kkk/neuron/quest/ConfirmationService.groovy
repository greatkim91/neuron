package com.kkk.neuron.quest

import com.kkk.neuron.NeuronException;
import com.kkk.neuron.auth.Role
import com.kkk.neuron.auth.UserRole
import com.kkk.neuron.quest.Delivery
import com.kkk.neuron.quest.Path
import com.kkk.neuron.quest.Relationship

class ConfirmationService {
	
	def pathService

    def register(newUser, pathId, email) {
		// find delivery and certi
		def aPath = Path.get(pathId)
		if (!aPath) {
			throw new NeuronException('no such path:' + pathId)
		}
		
		def delivery = Delivery.findByParentPathAndEmail aPath, email
		if (!delivery) {
			throw new NeuronException('no such delivery:' + email)
		}
		
		// create user
		if (!newUser.save(flush: true)) {
			return
		}
		
		def role = Role.findByAuthority('ROLE_USER')
		
		UserRole.create newUser, role, true
		
		// deliver
		pathService.deliver(aPath, newUser.id)
		
		// delete delivery
		delivery.delete()
		
		// add to friend
		new Relationship(owner:aPath.user, friend: newUser).save(flush: true)
    }
}
