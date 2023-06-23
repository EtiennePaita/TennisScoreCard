package com.epms.tennisscorecard.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.domain.models.Match
import com.epms.tennisscorecard.domain.models.MatchState
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.domain.models.PlayerScore
import com.epms.tennisscorecard.databinding.ActivityMatchBinding
import com.epms.tennisscorecard.databinding.AlertGetNameBinding
import com.epms.tennisscorecard.databinding.AlertWinnerBinding
import com.epms.tennisscorecard.ui.adapters.HistoryAdapter
import com.epms.tennisscorecard.ui.adapters.MatchAdapter
import com.epms.tennisscorecard.ui.viewModels.MatchViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMatchBinding
    private lateinit var opponent: Player
    private lateinit var user: Player
    private lateinit var match: Match
    private lateinit var adapter: MatchAdapter
    private var currentMatchEntityState: MatchState? = null
    private val matchViewModel: MatchViewModel by viewModels()

    companion object {
        private const val opponentKey = "opponent_player_key"
        private const val userKey = "user_player_key"

        fun newIntent(context: Context, jsonUserPlayer: String, jsonOpponentPlayer: String): Intent {
            return Intent(context, MatchActivity::class.java).apply {
                putExtra(opponentKey, jsonOpponentPlayer)
                putExtra(userKey, jsonUserPlayer)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupMatch()
        setRecyclerView()
        setUIListeners()
        setObservers()
    }

    private fun setupUI() {
        val gson = Gson()
        val sTypeToken = object : TypeToken<Player>(){}.type
        val jsonOpponentEntity = intent.getStringExtra(opponentKey)
        opponent = gson.fromJson(jsonOpponentEntity,sTypeToken)

        val jsonUserEntity = intent.getStringExtra(userKey)
        user = gson.fromJson(jsonUserEntity,sTypeToken)
    }

    private fun setupMatch() {
        match = Match(user, opponent)
        binding.player1Name.text = user.name
        binding.player2Name.text = opponent.name
    }

    private fun setUIListeners() {
        binding.buttonIncrementPlayer1.setOnClickListener {
            match.playerScoring(user)
        }
        binding.buttonIncrementPlayer2.setOnClickListener {
            match.playerScoring(opponent)
        }
    }

    private fun setObservers() {
        match.matchState.observe(this) {
            currentMatchEntityState = it
            this.adapter.setData(it)
            when (it) {
                is MatchState.InProgress -> {
                    updateUIScore(it)
                }
                is MatchState.IsOver -> {
                    updateUIScore(it)
                    binding.buttonIncrementPlayer1.isEnabled = false
                    binding.buttonIncrementPlayer2.isEnabled = false

                    matchViewModel.insertMatch(it, match.winningSets)

                    showVictoryPopup(it.winner.name)
                }
            }
        }
    }

    private fun setRecyclerView() {
        this.adapter = MatchAdapter()
        binding.matchSetsRecyclerview.adapter = this.adapter
        binding.matchSetsRecyclerview.setHasFixedSize(true)
    }

    private fun updateUIScore(matchState: MatchState) {
        binding.player1Score.text = getScoreBoardOf(matchState.player1State)
        binding.player2Score.text = getScoreBoardOf(matchState.player2State)
    }

    private fun getScoreBoardOf(playerScore: PlayerScore): String =
        if(playerScore.hasAdvantage()) "A" else playerScore.getPoints().toString()


    private fun showVictoryPopup(winnerName: String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        val alertdialogView = builder.create()
        val alertBinding = AlertWinnerBinding.inflate(layoutInflater)
        alertdialogView.setView(alertBinding.root)
        alertdialogView.window?.setBackgroundDrawableResource(R.color.transparent)

        alertBinding.winnerName.text = winnerName
        alertBinding.nextButton.setOnClickListener {
            finish()
        }

        alertdialogView.show()
    }

}