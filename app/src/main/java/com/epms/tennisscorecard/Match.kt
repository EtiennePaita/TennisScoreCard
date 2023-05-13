package com.epms.tennisscorecard

class Match(
    player1: Player,
    player2: Player
) {
    private var player1Score: PlayerScore = PlayerScore()
    private var player2Score: PlayerScore = PlayerScore()

    fun player1ScorePoint() {
        if (player2Score.getPointsScore() == 40 && player1Score.getPointsScore() == 40) {
            if (player1Score.hasAdvantage()) {
                player1Score.winPoint()
                player1Score.removeAdvantage()
            } else if (player2Score.hasAdvantage()) {
                player2Score.removeAdvantage()
            } else {
                player1Score.giveAdvantage()
            }
        } else {
            player1Score.winPoint()
        }
    }

    fun player2ScorePoint() {
        if (player2Score.getPointsScore() == 40 && player1Score.getPointsScore() == 40) {
            if (player2Score.hasAdvantage()) {
                player2Score.winPoint()
                player2Score.removeAdvantage()
            } else if (player1Score.hasAdvantage()) {
                player1Score.removeAdvantage()
            } else {
                player2Score.giveAdvantage()
            }
        } else {
            player2Score.winPoint()
        }
    }

    fun getPlayer1PointsScore() = player1Score.getPointsScore()

    fun getPlayer2PointsScore() = player2Score.getPointsScore()

}