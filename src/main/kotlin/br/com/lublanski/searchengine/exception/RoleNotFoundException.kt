package br.com.lublanski.searchengine.exception

data class RoleNotFoundException(override val message : String) : RuntimeException(message)


