package br.com.lublanski.searchengine.datamap

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class Team(
    @field:NotEmpty(message = "the nation name can't be null or empty")
    @field:Size(min = 3, max = 45)
    val nation: String,
    @field:Min(0)
    val minValuation: Long,
    @field:Min(0)
    val minTitlesWon: Int
)


