package com.epms.tennisscorecard.domain.models

import com.epms.tennisscorecard.data.local.entities.UserEntity

data class User(
    val id: Int,
    val name: String
)

fun User.toPlayer() = Player(this.id, this.name)
fun User.toUserEntity() = UserEntity(this.name).also { it.userId = this.id }
