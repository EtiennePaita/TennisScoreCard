package com.epms.tennisscorecard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Match(
    player1: Player,
    player2: Player,
    private val winningSets: Int = 2
) {
    private val player1Score: PlayerScore = PlayerScore(player1)
    private val player2Score: PlayerScore = PlayerScore(player2)
    private var matchFinished = false
    private var winner: Player? = null
    private val _matchState: MutableLiveData<MatchState> = MutableLiveData(MatchState.InProgress(player1Score,player2Score))
    val matchState: LiveData<MatchState> = _matchState

    init {
        if (player1.equals(player2)) throw Exception("The two players in the match must not be the same.")
    }

    /**
     * This function scores a point for the winner
     *
     * @param winner player that just win the point
     */
    @Throws(Exception::class)
    fun playerScoring(winner: Player) {
        if (matchFinished) return

        val winnerScore = findPlayerScoreOf(winner)
        val loserScore = findOpponentOf(winner)

        when (winnerScore.getCurrentGame()) {
            is TieBreak -> {
                tieBreakGameScoreHandler(winnerScore, loserScore)
            }
            else -> {
                gameScoreHandler(winnerScore, loserScore)
            }
        }
        updateMatchState()
    }

    /**
     * This function handles the game score on a tieBreak. Win a point and check if the game is won.
     *
     * @param winnerScore player that just win the point
     * @param loserScore player that just lose the point
     */
    private fun tieBreakGameScoreHandler(winnerScore: PlayerScore, loserScore: PlayerScore) {
        winnerScore.winPoint()
        if (winnerScore.getPoints() >= 7 && (winnerScore.getPoints() - loserScore.getPoints()) >= 2) {
            winnerScore.winGame()
            matchContinuationManager(winnerScore, loserScore)
        }
    }

    /**
     * This function handles the game score on a regular game. Win a point and check if the game is
     * won.
     *
     * @param winnerScore player that just win the point
     * @param loserScore player that just lose the point
     */
    private fun gameScoreHandler(winnerScore: PlayerScore, loserScore: PlayerScore) {
        if (winnerScore.getPoints() == 40 && loserScore.getPoints() == 40) { // handle advantages
            if (winnerScore.hasAdvantage()) {
                winnerScore.winGame()
                matchContinuationManager(winnerScore, loserScore)
            } else if (loserScore.hasAdvantage()) {
                loserScore.removeAdvantage()
            } else {
                winnerScore.giveAdvantage()
            }
        } else if (winnerScore.getPoints() == 40 && loserScore.getPoints() < 40) { // win this game
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
        } else {
            val isTieBreak = winnerScore.getCurrentSet().gameScore == 6 && loserScore.getCurrentSet().gameScore == 6
            winnerScore.nextGame(isTieBreak = isTieBreak)
            loserScore.nextGame(isTieBreak = isTieBreak)
            return
        }

        //Check if match is finished
        if (winnerScore.numberOfSetsWon() == winningSets) {
            matchFinished = true
            winner = winnerScore.player
        } else {
            winnerScore.nextSet()
            loserScore.nextSet()
        }
    }

    private fun updateMatchState() {
        winner?.let { theWinner ->
            _matchState.value = MatchState.IsOver(
                player1Score,
                player2Score,
                theWinner
            )
        } ?: run {
            _matchState.value = MatchState.InProgress(
                player1Score,
                player2Score
            )
        }
    }

    //Only for testing
    fun getPlayer1Score() = player1Score

    //Only for testing
    fun getPlayer2Score() = player2Score

    fun isMatchFinished() = matchFinished

    /**
     * This function search the [PlayerScore] of a player in the match and throw an exception if
     * the player is not found.
     *
     * @param player
     * @return the [PlayerScore] of the same player
     */
    @Throws(Exception::class)
    private fun findPlayerScoreOf(player: Player): PlayerScore =
        if (player1Score.player.id == player.id) player1Score
        else if (player2Score.player.id == player.id) player2Score
        else throw Exception("Player not found in this match")

    /**
     * This function search the [PlayerScore] of the opponent of a player in this match and throw
     * an exception if the opponent is not found.
     *
     * @param player
     * @return the [PlayerScore] of the opponent
     */
    @Throws(Exception::class)
    private fun findOpponentOf(player: Player): PlayerScore =
        if (player1Score.player.id != player.id) player1Score
        else if (player2Score.player.id != player.id) player2Score
        else throw Exception("Unexpected exception : Opponent not found")

}