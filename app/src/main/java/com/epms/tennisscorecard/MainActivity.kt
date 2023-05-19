package com.epms.tennisscorecard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val player1: Player = Player("Jean");
        val player2: Player = Player("Michel");
        val newMatch: Match = Match(player1 = player1, player2= player2);


        // Simulate match
        newMatch.playerScoring(player1, player2)
        newMatch.playerScoring(player2, player1)
        newMatch.playerScoring(player1, player2)
        newMatch.playerScoring(player1, player2)

    }
}