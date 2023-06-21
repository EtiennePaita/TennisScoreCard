package com.epms.tennisscorecard.domain.repositories

import com.epms.tennisscorecard.TennisScoreCardApp
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MatchRepository @Inject constructor() {
    private val matchDao = TennisScoreCardApp.matchDB?.MatchDao()

    fun getMatchHistory(): Flow<List<MatchEntity>?>? {
        return matchDao?.getMatchHistory()
    }

    fun insertMatch(match: MatchEntity) {
        matchDao?.insertMatch(match)
    }
}