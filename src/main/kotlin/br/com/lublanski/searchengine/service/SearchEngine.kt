package br.com.lublanski.searchengine.service


import br.com.lublanski.searchengine.api.FootballTeamsApiHandler
import br.com.lublanski.searchengine.api.ApiResponseRequestAttributes
import br.com.lublanski.searchengine.datamap.TeamsInfo
import org.springframework.stereotype.Service

@Service
class SearchEngine(val footballTeamsApiHandler: FootballTeamsApiHandler) {

    fun getFilteredNames(nation: String, minValue: Long, minTitlesWon: Int): TeamsInfo {
        val teams = getTeams(nation)

        val filteredList = filterByTitlesWonAndValuation(teams, minValue, minTitlesWon)

        return TeamsInfo(sortByValueThenName(filteredList).map { it.name })

    }

    private fun getTeams(nation: String): List<ApiResponseRequestAttributes>{
        val teamList = footballTeamsApiHandler.getAllPages(nation)

        if (teamList.isEmpty()){
            throw NoSuchElementException("no teams found for specific nation '${nation}'")
        }

        return  teamList
    }

    private fun filterByTitlesWonAndValuation(
        teams: List<ApiResponseRequestAttributes>,
        minValue: Long, minTitlesWon: Int
        ): List<ApiResponseRequestAttributes>{

        return teams.filter {
            it.estimatedValueNumeric >= minValue && it.numberOfLeagueTitlesWon >= minTitlesWon
        }

    }

    private fun sortByValueThenName(teams: List<ApiResponseRequestAttributes>): List<ApiResponseRequestAttributes>{

        return teams.sortedWith(compareByDescending<ApiResponseRequestAttributes> { it.estimatedValueNumeric }
                .thenBy { it.name })

    }

}

