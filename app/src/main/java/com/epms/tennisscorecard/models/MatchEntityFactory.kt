package com.epms.tennisscorecard.models

import com.epms.tennisscorecard.MatchState

object MatchEntityFactory {

    fun createMatchEntity(matchState: MatchState, winningSets: Int): MatchEntity {

        return MatchEntity(
            opponent = PlayerEntity(matchState.player2State.player.name),
            userScore = Score(matchState.player1State.getPoints(), matchState.player1State.getSets()),
            opponentScore = Score(matchState.player2State.getPoints(), matchState.player2State.getSets()),
            winningSets = winningSets,
            isOver = matchState is MatchState.IsOver
        )
    }

}