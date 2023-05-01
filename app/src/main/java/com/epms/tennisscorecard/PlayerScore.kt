package com.epms.tennisscorecard

class PlayerScore {
    private var pointsScore = 0
    private val gameScore = mutableListOf(0,0)

    fun winPoint() {
        when (pointsScore) {
            0 -> { pointsScore = 15 }
            15 -> { pointsScore = 30 }
            30 -> { pointsScore = 40 }
            40 -> {
                pointsScore = 0
                winGame()
            }
        }
    }

    private fun winGame() {
        val setCursor = if(gameScore[0] == 6) 1 else 0

        gameScore[setCursor] += 1
    }

    fun getPointsScore() = pointsScore

    fun getGameScore() = gameScore
}