package com.epms.tennisscorecard

import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.domain.models.PlayerScore
import com.epms.tennisscorecard.domain.models.TieBreak
import org.junit.Assert.assertEquals
import org.junit.Test

internal class PlayerScoreTest {

    @Test
    fun `player score should be 15`() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()

        assertEquals(15, playerScore.getPoints())
    }

    @Test
    fun `player score should be 40`() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()

        assertEquals(40, playerScore.getPoints())
    }

    @Test
    fun `player set WonScore should be 2`() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winSet()
        playerScore.nextSet()
        playerScore.winSet()

        assertEquals(2, playerScore.numberOfSetsWon())
    }

    @Test
    fun `player should have advantage`() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.giveAdvantage()

        assertEquals(true, playerScore.hasAdvantage())
    }

    @Test
    fun `player should not have advantage`() {
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
    fun `player GameScore should be a TieBreak`() {
        val player = Player(1, "John Smith")
        val playerScore = PlayerScore(player)

        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.nextGame(true)

        assertEquals(true, playerScore.getCurrentGame() is TieBreak)
    }

    @Test
    fun `player TieBreak GameScorePoints should be 5`() {
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