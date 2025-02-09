package br.com.lublanski.searchengine.service

import br.com.lublanski.searchengine.datamap.User
import br.com.lublanski.searchengine.datamap.UserDetail
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

    fun createUser(user : User) : User {
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun getUserById(id : String) : Optional<User> {
        return userRepository.findById(id)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByusername(username)
        return UserDetail(user)
    }

    fun addRole(userId : String, roleName : String) : User {

        val role = roleRepository.findByRoleName(roleName)
        userRepository.addRoleToUser(userId, role)

        val user : Optional<User> = userRepository.findById(userId)
        return user.get()

    }

}