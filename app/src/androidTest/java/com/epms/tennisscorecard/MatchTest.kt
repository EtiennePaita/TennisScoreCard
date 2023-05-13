package com.epms.tennisscorecard

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchTest {

    @Test
    fun score40_15forPlayer1() {
        val player1Score = PlayerScore()
        val player2Score = PlayerScore()
        player1Score.winPoint()
        player1Score.winPoint()
        player1Score.winPoint()
        player2Score.winPoint()
        assertEquals(15, player2Score.getPointsScore())
        assertEquals(40, player1Score.getPointsScore())
    }

    @Test
    fun matchPlayerPointsEquals40() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()

        assertEquals(40, match.getPlayer1PointsScore())
        assertEquals(40, match.getPlayer2PointsScore())
    }

    @Test
    fun player2ShouldHaveAdvantage() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()

        match.player2ScorePoint()

        //assertEquals(true, player2Score.hasAdvantage())
        //assertEquals(false, player1Score.hasAdvantage())
    }

    @Test
    fun playerPointsShouldBeEqualsTo40() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()

        match.player2ScorePoint()
        match.player1ScorePoint()

        //assertEquals(false, player1Score.hasAdvantage())
        //assertEquals(false, player2Score.hasAdvantage())
    }

    @Test
    fun player1ShouldHaveAdvantage() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()

        match.player2ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()

        //assertEquals(true, player1Score.hasAdvantage())
        //assertEquals(false, player2Score.hasAdvantage())
    }

    @Test
    fun player1ScoreShouldBe0() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()

        match.player2ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()

        assertEquals(0, match.getPlayer1PointsScore())
    }

    @Test
    fun player2ScoreShouldBe0() {
        val player1: Player = Player("Jean")
        val player2: Player = Player("Paul")
        val match = Match(player1, player2)

        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()
        match.player2ScorePoint()

        match.player2ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()
        match.player1ScorePoint()

        assertEquals(0, match.getPlayer2PointsScore())
    }

}