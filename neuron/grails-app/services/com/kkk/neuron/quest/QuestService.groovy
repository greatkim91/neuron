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
		
		rewardService.withdraw(
			quest.owner,
			quest.reward,
			"Withdraw for Quest # " + quest.id)
    }
}
