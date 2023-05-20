package com.epms.tennisscorecard

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchTest {

    @Test
    fun score40_15forPlayer1() {
        val player1Score = Player("John Smith")
        val player2Score = Player("John Cena")
        player1Score.winPoint()
        player1Score.winPoint()
        player1Score.winPoint()
        player2Score.winPoint()
        assertEquals(15, player2Score.getPoints())
        assertEquals(40, player1Score.getPoints())
    }

    @Test
    fun matchPlayerPointsEquals40() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)

        assertEquals(40, match.getPlayer1PointsScore())
        assertEquals(40, match.getPlayer2PointsScore())
    }

    @Test
    fun player2ShouldHaveAdvantage() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)

        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)

        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)


        assertEquals(true, player2.hasAdvantage())
        //assertEquals(false, player1Score.hasAdvantage())
    }

    @Test
    fun playerPointsShouldBeEqualsTo40() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)

        match.playerScoring(player2, player1)
        match.playerScoring(player1, player2)


        //assertEquals(false, player1Score.hasAdvantage())
        //assertEquals(false, player2Score.hasAdvantage())
    }

    @Test
    fun player1ShouldHaveAdvantage() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)

        match.playerScoring(player2, player1)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)

        //assertEquals(true, player1Score.hasAdvantage())
        //assertEquals(false, player2Score.hasAdvantage())
    }

    @Test
    fun player1ScoreShouldBe0() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)

        match.playerScoring(player2, player1)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)

        assertEquals(0, match.getPlayer1PointsScore())
    }

    @Test
    fun player2ScoreShouldBe0() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)
        match.playerScoring(player2, player1)

        match.playerScoring(player2, player1)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)

        assertEquals(0, match.getPlayer2PointsScore())
    }

    @Test
    fun checkIfMatchIsEnded() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        // SET 1
        // Game 1
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 2
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 3
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 4
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 5
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 6
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)

        // SET 2
        // Game 1
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 2
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 3
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 4
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 5
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 6
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)

        assertEquals(true, match.stateSet(team1 = player1, team2 = player2))
    }


    @Test
    fun checkWhoWin() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        // SET 1
        // Game 1
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 2
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 3
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 4
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 5
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 6
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)

        // SET 2
        // Game 1
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 2
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 3
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 4
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 5
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)
        // Game 6
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.playerScoring(player1, player2)
        match.stateSet(team1 = player1, team2 = player2)

        assertEquals(player1, match.whoWin(team1 = player1, team2 = player2))

    }
}