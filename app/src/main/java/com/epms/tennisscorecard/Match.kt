package com.epms.tennisscorecard

class Match (
    private var player1: Player,
    private var player2: Player,
) {
    fun playerScoring(winner: Player, loser: Player) {
        if (winner !== loser) {
            if (winner.getPointsScore() <= 40) {
                winner.winPoint();
                if (winner.getPointsScore() == 40 && loser.getPointsScore() <= 15) {
                    winner.nextSet()
                    loser.nextSet()
                }
            } else if (winner.getPointsScore() == 40 && loser.getPointsScore() == 40) {
                if (winner.hasAdvantage()) {
                    winner.winPoint()
                    winner.removeAdvantage()
                } else if (loser.hasAdvantage()) {
                    loser.removeAdvantage()
                } else {
                    winner.giveAdvantage()
                }
            } else if ((winner.getPointsScore() == 40 && loser.getPointsScore() == 30)
                || (winner.getPointsScore() >= 40 && (winner.getPointsScore() - loser.getPointsScore() < 2))
            ) {
                winner.winPoint()
                winner.nextSet()
                loser.nextSet()
            } else {
                winner.winPoint()
            }
        }
    }

    fun getPlayer1PointsScore() = player1.getPointsScore()

    fun getPlayer2PointsScore() = player2.getPointsScore()


    fun checkTieBreakCase(winner: Player) {
        if (player1.getSet() == 6 && player2.getSet() == 6) {

        }
    }


}