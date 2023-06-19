package com.epms.tennisscorecard.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

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