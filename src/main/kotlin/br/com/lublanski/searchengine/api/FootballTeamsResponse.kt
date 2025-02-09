package br.com.lublanski.searchengine.api

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
class FootballTeamsResponse(
    val page: Long,
    val totalPages: Long,

    @JsonProperty("data")
    val teamList: List<ApiResponseRequestAttributes>
)