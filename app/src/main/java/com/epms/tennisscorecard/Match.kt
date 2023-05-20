package com.epms.tennisscorecard

class Match (
    private var player1: Player,
    private var player2: Player,
) {
    fun playerScoring(winner: Player, loser: Player) {
        if (winner !== loser) {
            if (winner.getPoints() <= 40) {
                winner.winPoint();
                if (winner.getPoints() == 40 && loser.getPoints() <= 15) {
                    winner.winGame()
                    winner.nextGame()
                    loser.nextGame()
                } else if (winner.getPoints() == 40) {
                    winner.giveAdvantage()
                }
            } else if (winner.getPoints() == 40 && loser.getPoints() == 40) {
                if (winner.hasAdvantage()) {
                    winner.winPoint()
                    winner.removeAdvantage()
                } else if (loser.hasAdvantage()) {
                    loser.removeAdvantage()
                } else {
                    winner.giveAdvantage()
                    println(winner.hasAdvantage())
                }
            } else if ((winner.getPoints() == 40 && loser.getPoints() == 30)
                || (winner.getPoints() >= 40 && (winner.getPoints() - loser.getPoints() < 2))
            ) {
                winner.winPoint()
                winner.winGame()
                winner.nextGame()
                loser.nextGame()
            } else {
                winner.winPoint()
            }
        }
    }

    fun getPlayer1PointsScore() = player1.getPoints()

    fun getPlayer2PointsScore() = player2.getPoints()

    fun stateSet(team1: Player, team2: Player):Boolean {
        if (team1.getGames() >= 6 || team2.getGames() >= 6) {
            if ((team1.getGames() - team2.getGames()) >= 2) {
                return if (team1.getSetCursor() < (team1.getSets().size - 1)) {
                    team1.winSet()
                    team1.nextSet()
                    team2.nextSet()
                    false
                } else {
                    true
                }
            } else if ((team2.getGames() - team1.getGames()) >= 2) {
                return if (team2.getSetCursor() < (team2.getSets().size - 1)) {
                    team2.winSet()
                    team2.nextSet()
                    team1.nextSet()
                    false
                } else {
                    true
                }
                // Tie-break case
            } else if (team1.getGames() == 7) {
                return if (team1.getSetCursor() < (team1.getSets().size - 1)) {
                    team1.winSet()
                    team1.nextSet()
                    team2.nextSet()
                    false
                } else {
                    true
                }
                // Tie-break case
            } else if (team2.getGames() == 7) {
                return if (team2.getSetCursor() < (team2.getSets().size - 1)) {
                    team2.winSet()
                    team2.nextSet()
                    team1.nextSet()
                    false
                } else {
                    true
                }
            } else {
                return false
            }
            //gameScore[setCursor] += 1
        } else {
            return false
        }
    }

    fun matchEnded(team1: Player, team2: Player): Boolean {
        val noMoreSet = this.stateSet(team1 = team1, team2 = team2)
        // Someone has won two sets
        return if (team1.getSets().sum() - team2.getSets().sum() >= 2 && noMoreSet) {
            true
        } else if (team2.getSets().sum() - team1.getSets().sum() >= 2 && noMoreSet) {
            true
        } else if (team1.getSetCursor() == (team1.getSets().size - 1) || team1.getSets().sum() - team2.getSets().sum() < 2 &&noMoreSet) {
            team1.addSet()
            team2.addSet()
            false
        } else {
            false
        }
    }

    fun whoWin(team1: Player, team2: Player): Player? {
        return if (team1.getSets().sum() - team2.getSets().sum() >= 2) {
            team1
        } else if (team2.getSets().sum() - team1.getSets().sum() >= 2) {
            team2
        } else {
            null
        }
    }
}