package com.kkk.neuron.quest

class RewardHistory {
	
	Date date
	long amount
	String type			// D: deposit, W: withdraw
	long balance
	String note
	
	static belongsTo = [reward: Reward]

    static constraints = {
		date blank: false
		type blank: false, matches:"[DW]"
		note nullable: true
    }
	
	def beforeValidate() {
		checkDate()
	}
	
	def beforeInsert() {
		checkDate()
	}
	
	private checkDate() {
		if (!date) {
			date = new Date()
		}
	}
}
