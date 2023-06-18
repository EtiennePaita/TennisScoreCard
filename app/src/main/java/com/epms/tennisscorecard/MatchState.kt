package com.epms.tennisscorecard

/**
 * MatchState defines the current state of a match.
 */

sealed interface MatchState {
    val player1State: PlayerScore
    val player2State: PlayerScore

    data class IsOver(
        override val player1State: PlayerScore,
        override val player2State: PlayerScore,
        val winner: Player
    ): MatchState

    data class InProgress(
        override val player1State: PlayerScore,
        override val player2State: PlayerScore
    ): MatchState

}