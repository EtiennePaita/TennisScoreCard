package com.epms.tennisscorecard.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.epms.tennisscorecard.domain.models.Set

@Entity(
    tableName = "tsc_match",
    indices = [
        Index(value = ["match_id"], unique = true)
    ]
)
data class MatchEntity(
    @Embedded(prefix = "opponent_")
    val opponent: PlayerEntity,                     // Store only playerID ??
    @ColumnInfo(name = "user_score")
    val userScore: Score,
    @ColumnInfo(name = "opponent_score")
    val opponentScore: Score,
    @ColumnInfo(name = "winning_sets")
    val winningSets: Int,
    @ColumnInfo(name = "is_over")
    val isOver: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "match_id")
    var matchId: Int = 0
}

data class Score(
    val currentGamePoints: Int,
    val sets: List<Set>
)

/*sealed interface MatchState {
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

}*/

