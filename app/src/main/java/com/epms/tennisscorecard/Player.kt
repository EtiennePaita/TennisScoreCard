package com.epms.tennisscorecard

import com.epms.tennisscorecard.models.PlayerEntity

data class Player(
    val id: Int,
    val name: String
) {
    fun equals(player: Player): Boolean = this.id == player.id
}

fun Player.toPlayerEntity() = PlayerEntity(name)