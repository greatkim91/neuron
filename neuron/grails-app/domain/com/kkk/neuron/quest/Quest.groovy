package com.kkk.neuron.quest

import com.kkk.neuron.auth.User;

class Quest {

	String title
	String content
	User owner
	
	Date periodFrom
	Date periodTo
	long reward
	
	Date dateCreated
	Date lastUpdated
	
	static hasMany = [path: Path]
	
    static constraints = {
		title blank: false
		content blank: false
		owner blank: false
    }
}
