package com.kkk.neuron.quest



import grails.test.mixin.*

import org.junit.*

import com.kkk.neuron.auth.User

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RewardService)
class RewardServiceTests {

    void testSomething() {
		def user = new User()
		service.depoist(user, 100, "note 1")
		service.withdraw(user, 20, "note 2")
		service.depoist(user, 50, "note 3")
		
		def reward = service.read(user)
		
		assertEquals "balance should be 130", 130, reward.balance 
    }
}
