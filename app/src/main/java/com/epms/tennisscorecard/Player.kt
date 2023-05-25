package com.epms.tennisscorecard

data class Player(
    val id: Int,
    val name: String
) {
    fun equals(player: Player): Boolean = this.id == player.id
}
