package com.epms.tennisscorecard

data class Player(
    val id: Int,
    val name: String
    //var matchesWon: Int,
    //var matchesLost: Int,

) {
    fun equals(player: Player): Boolean = this.id == player.id
}