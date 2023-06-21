package com.epms.tennisscorecard.domain.models

import com.epms.tennisscorecard.data.local.entities.PlayerEntity

data class Player(
    val id: Int,
    val name: String
) {
    fun equals(player: Player): Boolean = this.id == player.id
}

fun Player.toPlayerEntity() = PlayerEntity(name)