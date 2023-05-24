package com.epms.tennisscorecard

/**
 * MatchState defines the current state of a match.
 */

sealed interface MatchState {
    val player1State: PlayerState
    val player2State: PlayerState

    data class IsOver(
        override val player1State: PlayerState,
        override val player2State: PlayerState,
        val winner: Player
    ): MatchState

    data class InProgress(
        override val player1State: PlayerState,
        override val player2State: PlayerState
    ): MatchState

}