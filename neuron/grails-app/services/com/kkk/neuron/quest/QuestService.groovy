package com.kkk.neuron.quest

import com.kkk.neuron.NeuronException

class QuestService {
	
	def rewardService

    def save(Quest quest) {
	
		def reward = rewardService.read(quest.owner)
		
		if (reward?.balance < quest.reward) {
			throw new NeuronException("Quest reward should be less than your reward");
		}
		
		if (!quest.save(flush: true)) {
			throw new NeuronException("Could not save the quest.");
		}
		
		def path = new Path(quest: quest, user: quest.owner)
		if (!path.save(flush:true)) {
			
		}
		
		rewardService.withdraw(
			quest.owner,
			quest.reward,
			"Withdraw for Quest # " + quest.id)
    }
}
