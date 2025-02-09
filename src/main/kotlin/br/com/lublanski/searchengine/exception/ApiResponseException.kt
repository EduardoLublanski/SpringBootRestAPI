package br.com.lublanski.searchengine.exception

data class ApiResponseException(override val message: String): RuntimeException()
