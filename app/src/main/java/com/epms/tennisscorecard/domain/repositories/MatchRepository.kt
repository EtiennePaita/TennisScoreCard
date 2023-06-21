package com.epms.tennisscorecard.domain.repositories

import com.epms.tennisscorecard.TennisScoreCardApp
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.data.local.entities.toMatchRecap
import com.epms.tennisscorecard.domain.factories.MatchEntityFactory
import com.epms.tennisscorecard.domain.models.MatchRecap
import com.epms.tennisscorecard.domain.models.MatchState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MatchRepository @Inject constructor() {
    private val matchDao = TennisScoreCardApp.matchDB?.MatchDao()

    fun getMatchHistory(): Flow<List<MatchRecap>?>? {
        return matchDao?.getMatchHistory()?.map { it?.map { matchEntity -> matchEntity.toMatchRecap() } }
    }

    fun insertMatch(matchState: MatchState, winningSets: Int) {
        matchDao?.insertMatch(MatchEntityFactory.createMatchEntity(matchState, winningSets))
    }
}