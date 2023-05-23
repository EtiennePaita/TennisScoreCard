package com.epms.tennisscorecard

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class PlayerScoreTest {

    @Test
    fun playerScoreShouldBe15() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()

        assertEquals(15, playerScore.getPoints())
    }

    @Test
    fun playerScoreShouldBe40() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()

        assertEquals(40, playerScore.getPoints())
    }

    @Test
    fun playerSetWonScoreShouldBe2() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winSet()
        playerScore.nextSet()
        playerScore.winSet()

        assertEquals(2, playerScore.numberOfSetsWon())
    }

    @Test
    fun playerShouldHaveAdvantage() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.giveAdvantage()

        assertEquals(true, playerScore.hasAdvantage())
    }

    @Test
    fun playerShouldNotHaveAdvantage() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.giveAdvantage()
        playerScore.removeAdvantage()

        assertEquals(false, playerScore.hasAdvantage())
    }

    @Test
    fun playerGameScoreShouldBeATieBreak() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.nextGame(true)

        assertEquals(true, playerScore.getCurrentGame() is TieBreak)
    }

    @Test
    fun playerTieBreakGameScorePointsShouldBe5() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.nextGame(true)
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()

        assertEquals(5, playerScore.getPoints())
    }
}