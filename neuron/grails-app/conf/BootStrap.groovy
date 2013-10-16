import com.kkk.neuron.auth.Role
import com.kkk.neuron.auth.User
import com.kkk.neuron.auth.UserRole
import com.kkk.neuron.quest.Relationship
import com.kkk.neuron.quest.Reward

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		
		newUser 'dykim', '1', 'DY', adminRole
		newUser 'dwkim', '1', 'DW', userRole
		newUser 'yjkim', '1', 'YJ', userRole
		newUser 'aa0', '1', 'AA0', userRole
		newUser 'aa1', '1', 'AA1', userRole
		newUser 'aa2', '1', 'AA2', userRole
		newUser 'aa3', '1', 'AA3', userRole
		newUser 'aa4', '1', 'AA4', userRole
		newUser 'aa5', '1', 'AA5', userRole
		newUser 'aa6', '1', 'AA6', userRole
		newUser 'aa7', '1', 'AA7', userRole
		newUser 'aa8', '1', 'AA8', userRole
		newUser 'aa9', '1', 'AA9', userRole
		
		new Reward(
			owner: User.findByUsername("dykim"),
			balance: 1000
			).save(flush: true)
		
			
		newRelationship "dykim", "dwkim"
		newRelationship "dykim", "yjkim"
		
		newRelationship "dwkim", "aa0"
		newRelationship "dwkim", "aa1"
		newRelationship "dwkim", "aa3"
		
		newRelationship "yjkim", "aa5"
		newRelationship "yjkim", "aa6"
		newRelationship "yjkim", "aa7"
		
		assert User.count() == 13
		assert Role.count() == 2
		assert UserRole.count() == 13
    }
	
	private newUser(String username, String password, String name, Role role) {
		def testUser = new User(username: username, password: password, name: name)
		testUser.save(flush: true)
		
		UserRole.create testUser, role, true
	}
	
	private newRelationship(String ownerName, String friendName) {
		def owner = User.findByUsername ownerName
		def friend = User.findByUsername friendName
		
		new Relationship(owner:owner, friend: friend).save(flush: true)
	}
	
    def destroy = {
    }
}
