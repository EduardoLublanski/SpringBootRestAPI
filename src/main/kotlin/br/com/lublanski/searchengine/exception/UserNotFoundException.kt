package br.com.lublanski.searchengine.exception

data class UserNotFoundException(override val message : String) : RuntimeException(message)


