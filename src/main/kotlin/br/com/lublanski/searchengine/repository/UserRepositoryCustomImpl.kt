package br.com.lublanski.searchengine.repository

import br.com.lublanski.searchengine.datamap.Role
import br.com.lublanski.searchengine.datamap.User
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update

class UserRepositoryCustomImpl(private val mongoTemplate: MongoTemplate) : UserRepositoryCustom {
    override fun addRoleToUser(userId: String, newRole: Role) {

        val query = Query(Criteria.where("id").`is`(userId))
        val update = Update().push("roles", newRole)

        mongoTemplate.updateFirst(query, update, User::class.java)
    }
}