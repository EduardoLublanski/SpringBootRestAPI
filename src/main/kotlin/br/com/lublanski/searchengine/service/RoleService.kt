package br.com.lublanski.searchengine.service

import br.com.lublanski.searchengine.datamap.Role
import br.com.lublanski.searchengine.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleRepository) {

    fun register(role: Role) : Role {

       return roleRepository.save(role)

    }

    fun getRole(name : String) : Role {

        return roleRepository.findByRoleName(name)

    }

}