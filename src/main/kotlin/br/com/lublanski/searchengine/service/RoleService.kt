package br.com.lublanski.searchengine.service

import br.com.lublanski.searchengine.datamap.Role
import br.com.lublanski.searchengine.exception.RoleNotFoundException
import br.com.lublanski.searchengine.repository.RoleRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RoleService(val roleRepository: RoleRepository) {

    fun register(role: Role): Role {
        validateRoleExistence(role.roleName)
        return roleRepository.save(role)

    }

    fun getRole(name: String): Role {

        val role = roleRepository.findByRoleName(name)

        if (!role.isPresent) throw RoleNotFoundException("role $name is not found")

        return role.get()
    }

    fun listAllRoles(): List<Role> {
        return roleRepository.findAll()
    }

    private fun validateRoleExistence(name: String) {

        roleRepository.findByRoleName(name).orElseThrow {
            RoleNotFoundException("role $name is not found")
        }

    }

}