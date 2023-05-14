package com.epms.tennisscorecard


class Match(
    player1: Player,
    player2: Player
) {
    /*private val _matchState = MutableLiveData<MatchState>()
    val matchState: LiveData<MatchState> = _matchState*/

    private var player1State: PlayerState
    private var player2State: PlayerState

    init {
        player1State = PlayerState(player1, PlayerScore())
        player2State = PlayerState(player2, PlayerScore())
        //_matchState.postValue(MatchState.InProgress(player1State, player2State))
    }

    fun player1ScorePoint() {
        if (player2State.score.getPointsScore() == 40 && player1State.score.getPointsScore() == 40) {
            if (player1State.score.hasAdvantage()) {
                player1State.score.winPoint()
                player1State.score.removeAdvantage()
            } else if (player2State.score.hasAdvantage()) {
                player2State.score.removeAdvantage()
            } else {
                player1State.score.giveAdvantage()
            }
        } else {
            player1State.score.winPoint()
        }

        //updateMatchState()
    }

    fun player2ScorePoint() {
        if (player2State.score.getPointsScore() == 40 && player1State.score.getPointsScore() == 40) {
            if (player2State.score.hasAdvantage()) {
                player2State.score.winPoint()
                player2State.score.removeAdvantage()
            } else if (player1State.score.hasAdvantage()) {
                player1State.score.removeAdvantage()
            } else {
                player2State.score.giveAdvantage()
            }
        } else {
            player2State.score.winPoint()
        }

        //updateMatchState()
    }

    private fun updateMatchState() {
        /*findWinner()?.let { winner ->
            _matchState.postValue(MatchState.IsOver(player1State, player2State, winner))
        } ?: run {
            _matchState.postValue(MatchState.InProgress(player1State, player2State))
        }*/
    }

    private fun findWinner(): Player? {
        val p1LastSet = player1State.score.getGameScore().last()
        val p2LastSet = player2State.score.getGameScore().last()

        return if(
            (p1LastSet == 6 && p2LastSet <= 4) ||
            (p1LastSet > 6 && (p1LastSet - p2LastSet) == 2)
        ) {
            player1State.player
        } else if(
            (p2LastSet == 6 && p1LastSet <= 4) ||
            (p2LastSet > 6 && (p2LastSet - p1LastSet) == 2)
        ) {
            player2State.player
        } else {
            null
        }
    }

    fun getPlayer1Score() = player1State.score
    fun getPlayer2Score() = player2State.score

}