package br.com.lublanski.searchengine.repository

import br.com.lublanski.searchengine.datamap.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String>, UserRepositoryCustom{
    fun findByusername(username: String?) : Optional<User>

}