package com.epms.tennisscorecard


//TODO : improvements :
//              - add a builder to modify the value of WINNING_SETS (2 or 3) OR just a parameter

class Match(
    player1: Player,
    player2: Player
) {
    private val player1Score: PlayerScore = PlayerScore(player1)
    private val player2Score: PlayerScore = PlayerScore(player2)
    private val WINNING_SETS: Int = 2
    var matchEnded = false // TODO : refactor match ended

    fun player1Scored() {
        playerScoring(player1Score, player2Score)
    }

    fun player2Scored() {
        playerScoring(player2Score, player1Score)
    }

    private fun playerScoring(winnerScore: PlayerScore, loserScore: PlayerScore) {
        if (winnerScore.getCurrentGame() is TieBreak) {
            tieBreakGameScoreHandler(winnerScore, loserScore)
        } else {
            gameScoreHandler(winnerScore, loserScore)
        }


        // TODO :
        //      - check le set en cours pour voir si gagné ou non
        //      - check si le match est fini ou non
        //          - si un player a 2 (ou 3) set isWon = true -> MATCH ENDED
        //          - sinon, si un playerScore.last() != null -> nextSet()

    }

    private fun tieBreakGameScoreHandler(winnerScore: PlayerScore, loserScore: PlayerScore) {
        winnerScore.winPoint()
        if (winnerScore.getPoints() >= 7 && (winnerScore.getPoints() - loserScore.getPoints()) >= 2) {
            winnerScore.winGame()
            matchContinuationManager(winnerScore, loserScore)
        }
    }

    private fun gameScoreHandler(winnerScore: PlayerScore, loserScore: PlayerScore) {
        if (winnerScore.getPoints() == 40 && loserScore.getPoints() == 40) {
            if (winnerScore.hasAdvantage()) {
                winnerScore.winGame()
                matchContinuationManager(winnerScore, loserScore)
            } else if (loserScore.hasAdvantage()) {
                loserScore.removeAdvantage()
            } else {
                winnerScore.giveAdvantage()
            }
        } else if (winnerScore.getPoints() == 40 && loserScore.getPoints() < 40) {
            winnerScore.winGame()
            matchContinuationManager(winnerScore, loserScore)
        } else {
            winnerScore.winPoint()
        }
    }

    /**
     * This function manage the match continuation. It checks the victory of the match, the
     * victory of the set and manage the continuation of the match (TieBreak, next set, etc.)
     *
     */
    private fun matchContinuationManager(winnerScore: PlayerScore, loserScore: PlayerScore) {

        if (winnerScore.getCurrentSet().gameScore == 7 || (winnerScore.getCurrentSet().gameScore == 6 && loserScore.getCurrentSet().gameScore < 5)) { // win the set
            winnerScore.winSet()
            loserScore.loseSet()
        } else if (
            winnerScore.getCurrentSet().gameScore == 6
            && loserScore.getCurrentSet().gameScore == 6
        ) {
            winnerScore.nextGame(isTieBreak = true)
            loserScore.nextGame(isTieBreak = true)
        } else {
            winnerScore.nextGame()
            loserScore.nextGame()
        }

        //End of match check
        if (winnerScore.numberOfSetsWon() == WINNING_SETS) {
            matchEnded = true
        } else {
            winnerScore.nextSet()
            loserScore.nextSet()
        }
    }

    fun getPlayer1Score() = player1Score

    fun getPlayer2Score() = player2Score

    private fun findPlayerScoreOf(player: Player): PlayerScore =
        if (player1Score.player.id == player.id) player1Score
        else if (player2Score.player.id == player.id) player2Score
        else throw Exception("Player not found in this match")

}