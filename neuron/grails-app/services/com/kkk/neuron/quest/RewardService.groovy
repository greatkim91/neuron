package com.kkk.neuron.quest

import com.kkk.neuron.NeuronException
import com.kkk.neuron.auth.User

class RewardService {

	def Reward read(User user) {
		return Reward.findByOwner(user)
	}
	
    def depoist(User user, long amount, String note) {
		def reward = Reward.findByOwner(user)
		if (!reward) {
			reward = new Reward(owner: user)
		}
		
		reward.deposit(amount, note)
		
		reward.addToHistories(
						new RewardHistory(
							reward: reward,
							date: new Date(),
							amount: amount,
							type: "D",
							balance: reward.balance,
							note: note))
		reward.save(flush:true)
    }
	
	def withdraw(User user, long amount, String note) {
		def reward = Reward.findByOwner(user)
		
		if (!reward) {
			reward = new Reward(owner: user)
		}
		
		reward.withdrwa(amount, note)
		
		reward.addToHistories(
						new RewardHistory(
							reward: reward,
							date: new Date(),
							amount: amount,
							type: "W",
							balance: reward.balance,
							note: note))
		
		if (!reward.save(flush:true)) {
			throw new NeuronException("Could not save reward")
		}
	}
}
