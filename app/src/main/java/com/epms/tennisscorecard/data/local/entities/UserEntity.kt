package com.epms.tennisscorecard.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.domain.models.User

@Entity(
    tableName = "tsc_user",
    indices = [
        Index(value = ["user_id"], unique = true)
    ]
)
data class UserEntity(
    @ColumnInfo(name = "user_name")
    val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Int = 0
}

fun UserEntity.toPlayer() = Player(this.userId, this.name)
fun UserEntity.toUser() = User(this.userId, this.name)