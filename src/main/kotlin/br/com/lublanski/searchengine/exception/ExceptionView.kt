package br.com.lublanski.searchengine.exception

import java.time.LocalDateTime

data class ExceptionView(
    val status: Int,
    val error: String,
    val message: String,
    val path: String
) {
    val dateError: LocalDateTime = LocalDateTime.now()
}


