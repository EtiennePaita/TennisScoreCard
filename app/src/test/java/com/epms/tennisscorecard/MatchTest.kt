package com.epms.tennisscorecard

import com.epms.tennisscorecard.domain.models.Match
import com.epms.tennisscorecard.domain.models.Player
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class MatchTest {

    @Test
    fun `creating match is the same player should throw error`() {
        val player1 = Player(1, "John Smith")

        assertThrows(Exception::class.java) {
            Match(player1, player1)
        }
    }

    @Test
    fun `player 1 and 2 gameScore points should be 0`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        assertEquals(0, match.getPlayer1Score().getPoints())
        assertEquals(0, match.getPlayer2Score().getPoints())
    }

    @Test
    fun `player1 GameScore points should be 15`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1)

        assertEquals(15, match.getPlayer1Score().getPoints())
    }

    @Test
    fun `player 2 GameScore points should be 0`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1)

        assertEquals(0, match.getPlayer2Score().getPoints())
    }

    @Test
    fun `player1 GameScore points should be 40`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)

        assertEquals(40, match.getPlayer1Score().getPoints())
    }

    @Test
    fun `player 1 setsWon score should be 2`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        for (i in 0 until 48) {
            match.playerScoring(winner = player1)
        }

        assertEquals(2, match.getPlayer1Score().numberOfSetsWon())
    }

    @Test
    fun `match should not be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        assertEquals(false, match.isMatchFinished())
    }

    @Test
    fun `match should be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        for (i in 0 until 48) {
            match.playerScoring(winner = player1)
        }

        assertEquals(true, match.isMatchFinished())
    }

    @Test
    fun `match with TieBreak should be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1)
        }


        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1)
        }

        assertEquals(true, match.isMatchFinished())
    }

    @Test
    fun `match with long TieBreak should be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1)
        }


        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1)
            match.playerScoring(winner = player2)
        }

        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)

        assertEquals(true, match.isMatchFinished())
    }

    @Test
    fun `match with long TieBreak should not be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1)
        }


        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1)
            match.playerScoring(winner = player2)
        }

        match.playerScoring(winner = player1)

        assertEquals(false, match.isMatchFinished())
    }

    @Test
    fun `match with TieBreak should not be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1)
        }

        assertEquals(false, match.isMatchFinished())
    }
    @Test
    fun `match with TieBreak player1 EndScore should be 6_7`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        //First set
        for (i in 0 until 24) {
            match.playerScoring(winner = player1)
        }

        //Second set 6 - 6
        for (i in 0 until 20) {
            match.playerScoring(winner = player1)
        }
        for (i in 0 until 20) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player2)
        }
        for (i in 0 until 4) {
            match.playerScoring(winner = player1)
        }

        //TieBreak
        for (i in 0 until 7) {
            match.playerScoring(winner = player1)
        }

        assertEquals(listOf(6,7), match.getPlayer1Score().getSets().map { it.gameScore })
    }

    @Test
    fun `match on 3 sets should not be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2, 3)

        for (i in 0 until 48) {
            match.playerScoring(winner = player1)
        }

        assertEquals(false, match.isMatchFinished())
    }

    @Test
    fun `match on 3 sets should be finished`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2, 3)

        for (i in 0 until 72) {
            match.playerScoring(winner = player1)
        }

        assertEquals(true, match.isMatchFinished())
    }


    @Test
    fun `player1 set games won should be 2`() {
        val player1 = Player(1, "John Smith")
        val player2 = Player(2, "John Cena")
        val match = Match(player1, player2)

        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)
        match.playerScoring(winner = player1)

        assertEquals(2, match.getPlayer1Score().getCurrentSet().gameScore)
    }

}