package com.epms.tennisscorecard

class Player(
    name: String
) {
    private var setCursor = 0
    private var points = 0
    private var games = 0
    private val sets = mutableListOf(0,0,0)
    private var hasAdvantage = false

    fun winPoint() {
        when (points) {
            0 -> { points = 15 }
            15 -> { points = 30 }
            30 -> { points = 40 }
            40 -> { points += 1 }
        }
    }

    fun losePoint() {
        when (points) {
            15 -> { points = 0 }
            30 -> { points = 15 }
            40 -> { points = 30 }
        }
    }

    fun giveAdvantage() { hasAdvantage = true }

    fun removeAdvantage() { hasAdvantage = false }

    fun nextSet() {
        if (setCursor < (sets.size - 1)) setCursor += 1
        points = 0
    }

    fun nextGame() {
        points=0
    }

    fun winGame() {
        games += 1
    }

    fun getGames(): Int = games

    fun getPoints(): Int = points

    fun getSetCursor(): Int = setCursor
    fun getSets() = sets

    fun winSet() {
        sets[setCursor] += 1
        //if (sets[setCursor] == 6 && setCursor < (sets.size - 1)) setCursor++
    }

    fun addSet() {
        sets.add(0)
    }

    fun hasAdvantage() = hasAdvantage
}