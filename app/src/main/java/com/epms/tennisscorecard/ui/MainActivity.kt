package com.epms.tennisscorecard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.epms.tennisscorecard.Match
import com.epms.tennisscorecard.MatchState
import com.epms.tennisscorecard.Player
import com.epms.tennisscorecard.PlayerScore
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var match: Match
    private lateinit var player1: Player
    private lateinit var player2: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTestMatch()
        setUIListeners()
    }

    private fun setUpTestMatch() {
        player1 = Player(1, "John Smith")
        player2 = Player(2, "Antoine De LaMarmotte")
        match = Match(player1, player2)
        match.matchState.observe(this) {
            when (it) {
                is MatchState.InProgress -> {
                    updateUIScore(it)
                }
                is MatchState.IsOver -> {
                    updateUIScore(it)
                    binding.player1ScoreButton.isEnabled = false
                    binding.player2ScoreButton.isEnabled = false
                    binding.winnerText.text = "Winner : ${it.winner.name}"
                }
            }
        }
    }

    private fun updateUIScore(matchState: MatchState) {
        //Get board score
        binding.player1Score.text = getScoreBoardOf(matchState.player1State)
        binding.player2Score.text = getScoreBoardOf(matchState.player2State)
    }

    private fun setUIListeners() {
        binding.player1ScoreButton.setOnClickListener {
            match.playerScoring(player1)
        }
        binding.player2ScoreButton.setOnClickListener {
            match.playerScoring(player2)
        }
    }

    private fun getScoreBoardOf(playerScore: PlayerScore): String {
        var scoreBoard = "${playerScore.player.name} : "
        playerScore.getSets().forEach {
            scoreBoard += "${it.gameScore} | "
        }
        scoreBoard += playerScore.getPoints()
        return scoreBoard
    }
}