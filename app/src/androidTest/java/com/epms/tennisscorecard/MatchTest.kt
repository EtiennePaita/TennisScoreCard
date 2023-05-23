package com.epms.tennisscorecard

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MatchTest {

    @Test
    fun player1EqualsPlayer2ShouldThrowError() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        assertThrows(Exception::class.java) {
            match.playerScoring(player1, player1)
        }
    }

    @Test
    fun player1And2GameScorePointsShouldBe0() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        assertEquals(0, match.getPlayer1Score().getPoints())
        assertEquals(0, match.getPlayer2Score().getPoints())
    }

    @Test
    fun player1GameScorePointsShouldBe15() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1, loser = player2)

        assertEquals(15, match.getPlayer1Score().getPoints())
    }

    @Test
    fun player2GameScorePointsShouldBe0() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1, loser = player2)

        assertEquals(0, match.getPlayer2Score().getPoints())
    }

    @Test
    fun player1GameScorePointsShouldBe40() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)

        assertEquals(40, match.getPlayer1Score().getPoints())
    }

    @Test
    fun player1SetsWonScoreShouldBe2() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        for (i in 0 until 48) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(2, match.getPlayer1Score().numberOfSetsWon())
    }

    @Test
    fun matchShouldNotBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        assertEquals(false, match.isMatchFinished())
    }

    @Test
    fun matchShouldBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        for (i in 0 until 48) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(true, match.isMatchFinished())
    }

    @Test
    fun matchWithTieBreakShouldBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1, loser = player2)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1, loser = player2)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1, loser = player2)
        }


        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(true, match.isMatchFinished())
    }

    @Test
    fun matchWithLongTieBreakShouldBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1, loser = player2)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1, loser = player2)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1, loser = player2)
        }


        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1, loser = player2)
            match.playerScoring(winner = player2, loser = player1)
        }

        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)

        assertEquals(true, match.isMatchFinished())
    }

    @Test
    fun matchWithLongTieBreakShouldNotBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1, loser = player2)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1, loser = player2)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1, loser = player2)
        }


        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1, loser = player2)
            match.playerScoring(winner = player2, loser = player1)
        }

        match.playerScoring(winner = player1, loser = player2)

        assertEquals(false, match.isMatchFinished())
    }

    @Test
    fun matchWithTieBreakShouldNotBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1, loser = player2)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1, loser = player2)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(false, match.isMatchFinished())
    }
    @Test
    fun matchWithTieBreakPlayer1EndScoreShouldBe6_7() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1, loser = player2)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1, loser = player2)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2, loser = player1)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1, loser = player2)
        }

        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(listOf(6,7), match.getPlayer1Score().getSets().map { it.gameScore })
    }

    @Test
    fun matchOn3SetsShouldNotBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2, 3)

        for (i in 0 until 48) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(false, match.isMatchFinished())
    }

    @Test
    fun matchOn3SetsShouldBeFinished() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2, 3)

        for (i in 0 until 72) {
            match.playerScoring(winner = player1, loser = player2)
        }

        assertEquals(true, match.isMatchFinished())
    }


    @Test
    fun player1SetGamesWonShouldBe2() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)
        match.playerScoring(winner = player1, loser = player2)

        assertEquals(2, match.getPlayer1Score().getCurrentSet().gameScore)
    }
}