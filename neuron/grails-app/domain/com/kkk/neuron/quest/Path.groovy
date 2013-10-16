package com.kkk.neuron.quest

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import com.kkk.neuron.auth.User

class Path {

	Quest quest
	User user
	long reward
	
	String answer
	
	boolean answered = false
	boolean chosen = false
	
	static belongsTo = [parent: Path]
	static hasMany = [children: Path]
	
    static constraints = {
		quest blank: false
		user blank: false
		answer nullable: true
		parent nullable: true
    }
	
	public void setAnswer(String anAnswer) {
		this.answer = anAnswer
		if (this.answer == null) {
			this.answered = false
		} else {
			this.answered = true
		}
	}
	
	def choose(long r, boolean c) {
		this.chosen = c
		choose(r)
	}
	
	def choose(long r) {
		reward = r * 0.8
		if (parent) {
			parent.choose(r - reward)
		}
	}
}
