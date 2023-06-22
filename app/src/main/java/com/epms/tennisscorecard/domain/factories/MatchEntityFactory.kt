package com.epms.tennisscorecard.domain.factories

import com.epms.tennisscorecard.domain.models.MatchState
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
import com.epms.tennisscorecard.data.local.entities.Score
import com.epms.tennisscorecard.data.local.entities.toPlayer
import com.epms.tennisscorecard.domain.models.MatchRecap

object MatchEntityFactory {

    fun createMatchEntity(matchState: MatchState, winningSets: Int): MatchEntity {

        return MatchEntity(
            user = PlayerEntity(matchState.player1State.player.name).also {
                it.playerId = matchState.player1State.player.id
            },
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
            isOver = matchState is MatchState.IsOver,
            winnerId = if(matchState is MatchState.IsOver) matchState.winner.id else null
        )
    }

    fun toMatchRecap(matchEntity: MatchEntity): MatchRecap {
        return MatchRecap(
            matchId = matchEntity.matchId,
            user = matchEntity.user.toPlayer(),
            opponent = matchEntity.opponent.toPlayer(),
            userScore = matchEntity.userScore,
            opponentScore = matchEntity.opponentScore,
            winningSets = matchEntity.winningSets,
            isOver = matchEntity.isOver,
            winnerId = matchEntity.winnerId
        )
    }

}