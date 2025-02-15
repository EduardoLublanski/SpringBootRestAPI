package br.com.lublanski.searchengine.datamap

import jakarta.validation.constraints.NotBlank
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @field:Id
    val id: ObjectId? = null,
    @field:NotBlank
    @Indexed(unique = true)
    val username: String,
    @field:NotBlank
    var password: String,
    val roles: MutableList<Role>
)
