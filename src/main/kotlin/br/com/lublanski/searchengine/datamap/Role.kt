package br.com.lublanski.searchengine.datamap

import jakarta.validation.constraints.Pattern
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Role(
    @field:Id
    val id: String? = null,
    @field:Pattern(regexp = "^ROLE_[A-Z]+$", message = "o role deve começar com ROLE_, deve conter apenas letras maiúsculas e underline")
    val roleName: String
)
