package com.epms.tennisscorecard

// Ideas of improvement :
//      - make Game a class : class Game(var hasAdvantage: Boolean)
//      - implement shouldWinGame method for each GameScore classes
//      - implement a copy method on GameScore interface. Like that classes outside PlayerScore wont
//      be able to modify the real GameScore


sealed interface GameScore {
    fun winPoint()
    fun getPoints(): Int
    //fun shouldWinGame(gameScore: GameScore): Boolean
}

class Game : GameScore {
    private var points = 0
    //private var hasAdvantage = false

    override fun winPoint() {
        when (points) {
            0 -> { points = 15 }
            15 -> { points = 30 }
            30 -> { points = 40 }
            40 -> { }
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