package com.epms.tennisscorecard

class PlayerScore() {
    private var setCursor = 0
    private var pointsScore = 0
    private val gameScore = mutableListOf(0,0)
    private var hasAdvantage = false

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

    fun losePoint() {
        when (pointsScore) {
            15 -> { pointsScore = 0 }
            30 -> { pointsScore = 15 }
            40 -> { pointsScore = 30 }
        }
    }

    fun resetPoints() {
        pointsScore = 0
    }

    fun giveAdvantage() { hasAdvantage = true }

    fun removeAdvantage() { hasAdvantage = false }

    fun nextSet() {
        if (setCursor < (gameScore.size - 1)) setCursor += 1
        pointsScore = 0
    }

    private fun winGame() {
        gameScore[setCursor] += 1
        // Move check in Match class ?
        if (gameScore[setCursor] == 6 && setCursor < (gameScore.size - 1)) setCursor++
    }

    fun getPointsScore() = pointsScore

    fun getGameScore() = gameScore
    fun hasAdvantage() = hasAdvantage
}