package br.com.lublanski.searchengine.controller

import br.com.lublanski.searchengine.config.SecurityConfiguration
import br.com.lublanski.searchengine.service.SearchEngine
import br.com.lublanski.searchengine.datamap.Team
import br.com.lublanski.searchengine.datamap.TeamsInfo
import br.com.lublanski.searchengine.repository.UserRepository
import br.com.lublanski.searchengine.service.UserService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/teams")
class TeamsListController(val searchEngine: SearchEngine) {

        @GetMapping("/{nation}")
        fun getFilteredTeamsNames(
                @Validated
                @PathVariable nation: String,
                @RequestParam minValuation: Long,
                @RequestParam minTitlesWon: Int,
        ): TeamsInfo {

                return searchEngine.getFilteredNames(nation, minValuation, minTitlesWon)

        }
}