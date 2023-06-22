package com.epms.tennisscorecard.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.epms.tennisscorecard.domain.models.Match
import com.epms.tennisscorecard.domain.models.MatchState
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.domain.models.PlayerScore
import com.epms.tennisscorecard.databinding.ActivityMatchBinding
import com.epms.tennisscorecard.ui.viewModels.MatchViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchActivity: BaseActivity() {
    private lateinit var binding: ActivityMatchBinding
    private lateinit var opponent: Player
    private lateinit var user: Player
    private lateinit var match: Match
    private var currentMatchEntityState: MatchState? = null
    private val matchViewModel: MatchViewModel by viewModels()

    companion object {
        private const val opponentKey = "opponent_player_key"
        private const val userKey = "user_player_key"

        fun newIntent(context: Context, jsonOpponentPlayer: String, jsonUserPlayer: String): Intent {
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
    }

    private fun setUIListeners() {
        binding.player1ScoreButton.setOnClickListener {
            match.playerScoring(user)
        }
        binding.player2ScoreButton.setOnClickListener {
            match.playerScoring(opponent)
        }
    }

    private fun setObservers() {
        match.matchState.observe(this) {
            currentMatchEntityState = it
            when (it) {
                is MatchState.InProgress -> {
                    updateUIScore(it)
                }
                is MatchState.IsOver -> {
                    updateUIScore(it)
                    binding.player1ScoreButton.isEnabled = false
                    binding.player2ScoreButton.isEnabled = false
                    //binding.winnerText.text = "Winner : ${it.winner.name}"

                    //TODO : showVictoryPopup -> save match -> finish()

                    matchViewModel.insertMatch(it, match.winningSets)
                }
            }
        }
    }

    private fun updateUIScore(matchState: MatchState) {
        //Get board score
        binding.player1Score.text = getScoreBoardOf(matchState.player1State)
        binding.player2Score.text = getScoreBoardOf(matchState.player2State)
    }
    private fun getScoreBoardOf(playerScore: PlayerScore): String {
        var scoreBoard = "${playerScore.player.name} : "
        playerScore.getSets().forEach {
            scoreBoard += "${it.gameScore} | "
        }
        scoreBoard += if(playerScore.hasAdvantage()) "A" else playerScore.getPoints()
        return scoreBoard
    }

}