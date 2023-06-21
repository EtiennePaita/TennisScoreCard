package com.epms.tennisscorecard.domain.factories

import com.epms.tennisscorecard.domain.models.MatchState
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
import com.epms.tennisscorecard.data.local.entities.Score

object MatchEntityFactory {

    fun createMatchEntity(matchState: MatchState, winningSets: Int): MatchEntity {

        return MatchEntity(
            opponent = PlayerEntity(matchState.player2State.player.name).also {
                it.playerId = matchState.player2State.player.id
            },
            userScore = Score(
                matchState.player1State.getPoints(),
                matchState.player1State.getSets()
            ),
            opponentScore = Score(
                matchState.player2State.getPoints(),
                matchState.player2State.getSets()
            ),
            winningSets = winningSets,
            isOver = matchState is MatchState.IsOver
        )
    }

}