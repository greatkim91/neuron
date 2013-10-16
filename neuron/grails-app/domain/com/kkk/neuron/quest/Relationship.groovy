package com.kkk.neuron.quest

import org.apache.commons.lang.builder.HashCodeBuilder

import com.kkk.neuron.auth.User

class Relationship {

	User owner
	User friend
	
    static constraints = {
		friend unique: 'friend'
    }
	
}
