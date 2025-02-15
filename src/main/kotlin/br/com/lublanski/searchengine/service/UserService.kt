package br.com.lublanski.searchengine.service

import br.com.lublanski.searchengine.datamap.User
import br.com.lublanski.searchengine.datamap.UserDetail
import br.com.lublanski.searchengine.exception.UserNotFoundException
import br.com.lublanski.searchengine.repository.RoleRepository
import br.com.lublanski.searchengine.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    val userRepository : UserRepository,
    val passwordEncoder : PasswordEncoder,
    val roleRepository: RoleRepository
) : UserDetailsService {

    fun createUser(newUser : User) : User {
        validateUserExistence(newUser.username)
        return userRepository.save(newUser)
    }

    fun getUserById(id : String) : Optional<User> {
        return userRepository.findById(id)
    }

    fun getAllUsers(): List<User> {
        println(userRepository.findAll().get(0).roles.get(0))
        return userRepository.findAll()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByusername(username).orElseThrow {
            UserNotFoundException("user ${username} not found")
        }
        return UserDetail(user)
    }

    fun addRoleToUser(userId : String, roleName : String) : User {

        val role = roleRepository.findByRoleName(roleName).get()
        userRepository.addRoleToUser(userId, role)

        val user : Optional<User> = userRepository.findById(userId)
        return user.get()

    }
    private fun validateUserExistence(username : String) {
        userRepository.findByusername(username).orElseThrow {
            UserNotFoundException("user ${username} not found")
        }
    }
}