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
}