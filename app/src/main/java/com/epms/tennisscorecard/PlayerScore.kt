package com.epms.tennisscorecard

class PlayerScore {
    private var pointsScore = 0

    fun winPoint() {
        when (pointsScore) {
            0 -> { pointsScore = 15 }
            15 -> { pointsScore = 30 }
            30 -> { pointsScore = 40 }
            40 -> { pointsScore = 0 }
        }
    }

    fun getPointsScore() = pointsScore
}