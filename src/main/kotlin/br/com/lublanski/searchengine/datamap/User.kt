package br.com.lublanski.searchengine.datamap

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field


@Document
data class User(
    @Id
    val id: String? = null,
    @field:NotBlank
    @Indexed(unique = true)
    val username: String,
    @field:NotBlank
    var password: String,
    val roles: MutableList<Role>
)
