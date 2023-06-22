package com.epms.tennisscorecard.domain.models

import com.epms.tennisscorecard.data.local.entities.Score

data class MatchRecap(
    var matchId: Int = 0,
    val user: Player,
    val opponent: Player,
    val userScore: Score,
    val opponentScore: Score,
    val winningSets: Int,
    val isOver: Boolean,
    val winnerId: Int?
)
