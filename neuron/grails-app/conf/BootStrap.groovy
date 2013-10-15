import com.kkk.neuron.auth.Role
import com.kkk.neuron.auth.User
import com.kkk.neuron.auth.UserRole;

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		
		newUser 'dykim', '1234', 'DY', adminRole
		newUser 'dwkim', '1234', 'DW', userRole
		newUser 'yjkim', '1234', 'YJ', userRole
		newUser 'aa0', '1234', 'AA0', userRole
		newUser 'aa1', '1234', 'AA1', userRole
		newUser 'aa2', '1234', 'AA2', userRole
		newUser 'aa3', '1234', 'AA3', userRole
		newUser 'aa4', '1234', 'AA4', userRole
		newUser 'aa5', '1234', 'AA5', userRole
		newUser 'aa6', '1234', 'AA6', userRole
		newUser 'aa7', '1234', 'AA7', userRole
		newUser 'aa8', '1234', 'AA8', userRole
		newUser 'aa9', '1234', 'AA9', userRole
		
		assert User.count() == 13
		assert Role.count() == 2
		assert UserRole.count() == 13
    }
	
	private newUser(String username, String password, String name, Role role) {
		def testUser = new User(username: username, password: password, name: name)
		testUser.save(flush: true)
		
		UserRole.create testUser, role, true
	}
	
    def destroy = {
    }
}
