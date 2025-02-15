package br.com.lublanski.searchengine.config

import br.com.lublanski.searchengine.datamap.Role
import br.com.lublanski.searchengine.datamap.User
import br.com.lublanski.searchengine.service.RoleService
import br.com.lublanski.searchengine.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val userService: UserService,
    private val roleService: RoleService,
    private val passwordEncoder: PasswordEncoder
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        if (roleService.roleRepository.findByRoleName("ROLE_SUPERADM").isEmpty) {

            val roleSuperAdm = Role(roleName = "ROLE_SUPERADM")
            roleService.roleRepository.save(roleSuperAdm)

            if (userService.userRepository.findByusername("superadm").isEmpty) {

                val user = User(
                    username = "superadm",
                    password = passwordEncoder.encode("u$3r\$up3r@dm"),
                    roles = mutableListOf<Role>()
                )
                val roleSuperAdmFromMongoDB = roleService.roleRepository.findByRoleName(roleSuperAdm.roleName)
                user.roles.add(roleSuperAdmFromMongoDB.get())

                userService.userRepository.save(user)

            }

        }

        if (roleService.roleRepository.findByRoleName("ROLE_SUPERADM").isEmpty) {

            val roleUser = Role(roleName = "ROLE_USER")
            roleService.roleRepository.save(roleUser)

        }

        if (roleService.roleRepository.findByRoleName("ROLE_SUPERADM").isEmpty) {

            val roleAdm = Role(roleName = "ROLE_ADM")
            roleService.roleRepository.save(roleAdm)

        }

        if (roleService.roleRepository.findByRoleName("ROLE_SUPERADM").isEmpty) {

            val roleBanned = Role(roleName = "ROLE_BANNED")
            roleService.roleRepository.save(roleBanned)

        }

    }

}