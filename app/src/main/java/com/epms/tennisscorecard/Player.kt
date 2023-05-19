package com.epms.tennisscorecard

class Player(
    name: String
) {
    private var setCursor = 0
    private var pointsScore = 0
    private val gameScore = mutableListOf(0,0)
    private var hasAdvantage = false

    fun winPoint() {
        when (pointsScore) {
            0 -> { pointsScore = 15 }
            15 -> { pointsScore = 30 }
            30 -> { pointsScore = 40 }
            40 -> { pointsScore += 1 }
        }
    }

    fun losePoint() {
        when (pointsScore) {
            15 -> { pointsScore = 0 }
            30 -> { pointsScore = 15 }
            40 -> { pointsScore = 30 }
        }
    }

    fun giveAdvantage() { hasAdvantage = true }

    fun removeAdvantage() { hasAdvantage = false }

    private fun winGame() {
        gameScore[setCursor] += 1
        if (gameScore[setCursor] == 6 && setCursor < (gameScore.size - 1)) setCursor++
    }

    fun nextSet() {
        if (setCursor < (gameScore.size - 1)) setCursor += 1
        pointsScore = 0
    }

    fun getPointsScore() = pointsScore

    fun getSet() = setCursor
    fun getGameScore() = gameScore
    fun hasAdvantage() = hasAdvantage
}