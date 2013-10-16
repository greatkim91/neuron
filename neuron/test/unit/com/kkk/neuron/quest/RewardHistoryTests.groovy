package com.kkk.neuron.quest

import grails.test.mixin.TestFor


/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(RewardHistory)
class RewardHistoryTests {

    void testTypeD() {
		def r = new Reward()
		def rh = new RewardHistory(reward: r, type:"D")
		assertTrue "Validation should be true", rh.validate()
    }
	
	void testTypeW() {
		def r = new Reward()
		def rh = new RewardHistory(reward: r, type:"W")
		assertTrue "Validation should be true", rh.validate()
	}
	
	void testTypeX() {
		def r = new Reward()
		def rh = new RewardHistory(reward: r, type:"X")
		assertFalse "Validation should be false", rh.validate()
	}
}
