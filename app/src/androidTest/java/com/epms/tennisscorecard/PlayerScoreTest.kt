package com.epms.tennisscorecard

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class PlayerScoreTest {
    @Test
    fun win1PointShouldScore15() {
        val playerScore = PlayerScore()
        playerScore.winPoint()
        assertEquals(15, playerScore.getPointsScore())
    }

    @Test
    fun win2PointsShouldScore30() {
        val playerScore = PlayerScore()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(30, playerScore.getPointsScore())
    }

    @Test
    fun win3PointsShouldScore40() {
        val playerScore = PlayerScore()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(40, playerScore.getPointsScore())
    }

    @Test
    fun win4PointsShouldScore0() {
        val playerScore = PlayerScore()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(0, playerScore.getPointsScore())
    }

    @Test
    fun win4PointsShouldScore1Game() {
        val playerScore = PlayerScore()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        playerScore.winPoint()
        assertEquals(listOf(1,0), playerScore.getGameScore())
    }

    @Test
    fun win24PointsShouldScore6Game() {
        val playerScore = PlayerScore()
        for (i in 0 until 24) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,0), playerScore.getGameScore())
    }

    @Test
    fun win27PointsShouldScore6GameAnd40Points() {
        val playerScore = PlayerScore()
        for (i in 0 until 27) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,0), playerScore.getGameScore())
        assertEquals(40, playerScore.getPointsScore())
    }

    @Test
    fun win28PointsShouldScore7Game() {
        val playerScore = PlayerScore()
        for (i in 0 until 28) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,1), playerScore.getGameScore())
    }

    @Test
    fun win48PointsShouldScore12Game() {
        val playerScore = PlayerScore()
        for (i in 0 until 48) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,6), playerScore.getGameScore())
    }

    @Test
    fun win52PointsShouldScore13Game() {
        val playerScore = PlayerScore()
        for (i in 0 until 52) {
            playerScore.winPoint()
        }
        assertEquals(listOf(6,7), playerScore.getGameScore())
    }
}