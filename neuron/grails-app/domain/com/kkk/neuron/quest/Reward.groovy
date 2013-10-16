package com.kkk.neuron.quest

import com.kkk.neuron.auth.User;

class Reward {
	
	User owner
	
	long balance
	
	static hasMany = [histories: RewardHistory]

    static constraints = {
		owner blank: false, unique: true
    }
	
	
	def deposit(long amount, String note) {
		balance = balance +  amount
//		histories.add(
//			new RewardHistory(
//				reward: this,
//				amount: amount, 
//				type: "D", 
//				balance: balance, 
//				note: note))
	}
	
	def withdrwa(long amount, String note) {
		balance = balance -  amount
//		histories.add(
//			new RewardHistory(
//				reward: this,
//				amount: amount,
//				type: "W",
//				balance: balance,
//				note: note))
	}
}
