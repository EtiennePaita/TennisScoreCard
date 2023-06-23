package com.epms.tennisscorecard.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

import com.epms.tennisscorecard.domain.models.MatchRecap
import com.epms.tennisscorecard.domain.models.Set

@Entity(
    tableName = "tsc_match",
    indices = [
        Index(value = ["match_id"], unique = true)
    ]
)
data class MatchEntity(
    @Embedded(prefix = "user_")
    val user: UserEntity,
    @Embedded(prefix = "opponent_")
    val opponent: PlayerEntity,
    @ColumnInfo(name = "user_score")
    val userScore: Score,
    @ColumnInfo(name = "opponent_score")
    val opponentScore: Score,
    @ColumnInfo(name = "winning_sets")
    val winningSets: Int,
    @ColumnInfo(name = "is_over")
    val isOver: Boolean,
    @ColumnInfo(name = "winner_id")
    val winnerId: Int?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "match_id")
    var matchId: Int = 0
}

fun MatchEntity.toMatchRecap(): MatchRecap = MatchRecap(
    matchId = this.matchId,
    user = this.user.toPlayer(),
    opponent = this.opponent.toPlayer(),
    userScore = this.userScore,
    opponentScore = this.opponentScore,
    winningSets = this.winningSets,
    isOver = this.isOver,
    winnerId = this.winnerId
)

data class Score(
    val currentGamePoints: Int,
    val sets: List<Set>
)
