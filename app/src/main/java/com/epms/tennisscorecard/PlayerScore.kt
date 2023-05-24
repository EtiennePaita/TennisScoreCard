package com.epms.tennisscorecard

class PlayerScore(
    val player: Player
) {
    private var currentGame: GameScore = Game()
    private val sets = mutableListOf(Set())
    private var hasAdvantage = false

    fun winPoint() {
        currentGame.winPoint()
    }

    fun giveAdvantage() { hasAdvantage = true }

    fun removeAdvantage() { hasAdvantage = false }

    // TODO : maybe throw an exception if sets.last() < 6 ?
    fun winSet() {
        sets.last().isWon = true
    }

    fun loseSet() {
        sets.last().isWon = false
    }

    fun nextSet() {
        sets.add(Set())
        removeAdvantage()
        nextGame()
    }

    fun winGame() {
        sets.last().gameScore += 1
        removeAdvantage()
    }

    fun nextGame(isTieBreak: Boolean = false) {
        currentGame = if (isTieBreak) TieBreak() else Game()
        removeAdvantage()
    }


    fun getCurrentGame() = currentGame

    fun getPoints(): Int = currentGame.getPoints()

    fun getCurrentSet() = sets.last()

    fun getSets() = sets

    fun numberOfSetsWon(): Int {
        var counter = 0
        sets.forEach { if (it.isWon == true) counter++ }
        return counter
    }

    fun hasAdvantage() = hasAdvantage
}