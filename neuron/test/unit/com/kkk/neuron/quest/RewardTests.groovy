package com.kkk.neuron.quest



import grails.test.mixin.TestFor

import com.kkk.neuron.auth.User

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reward)
class RewardTests {

    void testSomething() {
		def user = new User();
		Reward reward = new Reward(owner: user, balance: 100)
		
		reward.deposit(20, "D 20")
		reward.withdrwa(30, "W 30")
		
		assertEquals "balance should be 90", 90, reward.balance  
    }
}
