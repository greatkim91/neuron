package com.kkk.neuron.quest

import com.kkk.neuron.auth.User;

class Question {

	String title
	String content
	User questioner
	
	Date periodFrom
	Date periodTo
	long reward
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [path: Path]
	
    static constraints = {
		title blank: false
		content blank: false
		questioner blank: false
    }
}
