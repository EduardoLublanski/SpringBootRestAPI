package br.com.lublanski.searchengine.api

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ApiResponseRequestAttributes(
    val name: String,
    val estimatedValueNumeric: Long,
    val numberOfLeagueTitlesWon: Long
)
