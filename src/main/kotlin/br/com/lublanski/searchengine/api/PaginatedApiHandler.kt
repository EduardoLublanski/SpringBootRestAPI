package br.com.lublanski.searchengine.api

interface PaginatedApiHandler<T,V> {
    val uri: String
    val firstPage: Long
    val noPages: Long
    fun getAllPages(propertyName: String): List<T>
    fun getResponse(propertyName: String, page: Long): V?
}