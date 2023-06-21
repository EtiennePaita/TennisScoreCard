package com.epms.tennisscorecard.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.epms.tennisscorecard.domain.models.Player

@Entity(
    tableName = "tsc_player",
    indices = [
        Index(value = ["player_id"], unique = true)
    ]
)
data class PlayerEntity(
    @ColumnInfo(name = "player_name")
    val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "player_id")
    var playerId: Int = 0
}

fun PlayerEntity.equals(playerEntity: PlayerEntity): Boolean = this.playerId == playerEntity.playerId
fun PlayerEntity.toPlayer(): Player = Player(this.playerId, this.name)