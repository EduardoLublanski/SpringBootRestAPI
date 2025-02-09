package br.com.lublanski.searchengine.repository

import br.com.lublanski.searchengine.datamap.Role
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : MongoRepository<Role, String> {
    fun findByRoleName(roleName : String?) : Role
}