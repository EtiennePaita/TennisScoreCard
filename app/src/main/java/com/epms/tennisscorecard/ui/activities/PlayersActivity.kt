package com.epms.tennisscorecard.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.epms.tennisscorecard.databinding.ActivityPlayersBinding
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.ui.adapters.PlayersAdapter
import com.epms.tennisscorecard.ui.viewModels.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersActivity: BaseActivity(), PlayersAdapter.PlayersInterface {
    private lateinit var binding: ActivityPlayersBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var playersAdapter: PlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerViewModel.getPlayers()
        setupUI()
        setObservers()
    }

    private fun setupUI() {
        this.playersAdapter = PlayersAdapter(this)
        binding.eventsRecyclerview.adapter = this.playersAdapter
        binding.eventsRecyclerview.setHasFixedSize(true)
    }

    private fun setObservers() {
        playerViewModel.players.observe(this) {
            playersAdapter.setData(it)
        }
    }

    override fun onPlayerClick(eventEntity: Player) {
        // Do something
    }
}