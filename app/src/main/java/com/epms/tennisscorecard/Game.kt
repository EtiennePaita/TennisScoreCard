package com.epms.tennisscorecard

sealed interface GameScore {
    fun winPoint()
    fun getPoints(): Int
}

class Game : GameScore {
    private var points = 0

    override fun winPoint() {
        when (points) {
            0 -> points = 15
            15 -> points = 30
            30 -> points = 40
        }
    }

    override fun getPoints(): Int = points
}

class TieBreak : GameScore {
    private var points: Int = 0

    override fun winPoint() {
        points++
    }

    override fun getPoints(): Int = points
}