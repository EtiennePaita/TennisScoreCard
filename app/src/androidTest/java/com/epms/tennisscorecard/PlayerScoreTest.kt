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
    fun playerGameScorePointsShouldBe5() {
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

    /*@Test
    fun win1PointShouldScore15() {
        val playerScore = Player("John Smith")
        playerScore.winPoint()
        assertEquals(15, playerScore.getPoints())
    }

    @Test
    fun win2PointsShouldScore30() {
        val playerScore = Player("John Smith")
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(30, playerScore.getPoints())
    }

    @Test
    fun win3PointsShouldScore40() {
        val playerScore = Player("John Smith")
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(40, playerScore.getPoints())
    }

    @Test
    fun win4PointsShouldScore0() {
        val playerScore = Player("John Smith")
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(0, playerScore.getPoints())
    }

    @Test
    fun win4PointsShouldScore1Game() {
        val playerScore = Player("John Smith")
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(listOf(1,0,0), playerScore.getSets())
    }

    @Test
    fun win24PointsShouldScore6Game() {
        val playerScore = Player("John Smith")
        for (i in 0 until 24) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,0,0), playerScore.getSets())
    }

    @Test
    fun win27PointsShouldScore6GameAnd40Points() {
        val playerScore = Player("John Smith")
        for (i in 0 until 27) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,0,0), playerScore.getSets())
        assertEquals(40, playerScore.getPoints())
    }

    @Test
    fun win28PointsShouldScore7Game() {
        val playerScore = Player("John Smith")
        for (i in 0 until 28) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,1,0), playerScore.getSets())
    }

    @Test
    fun win48PointsShouldScore12Game() {
        val playerScore = Player("John Smith")
        for (i in 0 until 48) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,6,0), playerScore.getSets())
    }

    @Test
    fun win52PointsShouldScore13Game() {
        val playerScore = Player("John Smith")
        for (i in 0 until 52) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,7,0), playerScore.getSets())
    }*/
}