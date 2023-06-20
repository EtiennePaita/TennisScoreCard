package com.epms.tennisscorecard.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.epms.tennisscorecard.Match
import com.epms.tennisscorecard.Player
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.databinding.ActivityMainBinding
import com.epms.tennisscorecard.models.MatchEntity
import com.epms.tennisscorecard.models.PlayerEntity
import com.epms.tennisscorecard.models.toPlayer
import com.epms.tennisscorecard.viewModels.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var match: Match
    private lateinit var player1: Player
    private lateinit var player2: Player
    private var allPlayers: List<PlayerEntity>? = null
    private var matchHistory: List<MatchEntity>? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getPlayers()
        mainViewModel.getAllMatches()
        setUpTestMatch()
        setUIListeners()
        setObservers()
    }

    private fun setUpTestMatch() {
        player1 = Player(1, "John Smith")
        player2 = Player(2, "Antoine De LaMarmotte")
        match = Match(player1, player2)
    }

    private fun setUIListeners() {
        binding.createPlayerButton.setOnClickListener {
            if (!binding.playerNameEditText.text.isNullOrBlank()) {
                mainViewModel.insertPlayer(PlayerEntity(binding.playerNameEditText.text.toString()))
                binding.playerNameEditText.text = null
            }
        }
        binding.goToPlayersButton.setOnClickListener {
            startActivity(Intent(this, PlayersActivity::class.java))
        }
        binding.startMatchButton.setOnClickListener {
            allPlayers?.let { players ->
                if(players.size < 2) return@setOnClickListener
                val gson = Gson()
                val p1 = gson.toJson(players[0].toPlayer())
                val p2 = gson.toJson(players[1].toPlayer())
                startActivity(MatchActivity.newIntent(this, p1, p2))
            }
        }
    }

    private fun setObservers() {
        mainViewModel.players.observe(this) {
            allPlayers = it
            binding.playerCounterText.text = "${getString(R.string.players_registered)} : ${it?.size ?: 0}"
        }
        mainViewModel.matches.observe(this) {
            matchHistory = it
            binding.matchCounterText.text = "Match played : ${it?.size ?: 0}"
        }
    }
}