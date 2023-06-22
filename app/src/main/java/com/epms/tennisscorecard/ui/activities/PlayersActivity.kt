package com.epms.tennisscorecard.ui.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.databinding.ActivityPlayersBinding
import com.epms.tennisscorecard.databinding.AlertGetNameBinding
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.ui.adapters.PlayersAdapter
import com.epms.tennisscorecard.ui.viewModels.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersActivity: AppCompatActivity(), PlayersAdapter.PlayersInterface {
    private lateinit var binding: ActivityPlayersBinding
    private val playerViewModel: PlayerViewModel by viewModels()
    private lateinit var playersAdapter: PlayersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerViewModel.getPlayers()
        setupUI()
        setRecyclerView()
        setUIListeners()
        setObservers()
    }

    private fun setupUI() {
        binding.header.headerTitle.text = getString(R.string.companions)
        binding.noPlayersTextView.text = getString(R.string.players_not_found_error)
    }

    private fun setRecyclerView() {
        this.playersAdapter = PlayersAdapter(this)
        binding.companionsRecyclerview.adapter = this.playersAdapter
        binding.companionsRecyclerview.setHasFixedSize(true)
    }

    private fun setUIListeners() {
        binding.header.headerCtaButton.setOnClickListener {
            finish()
        }
        binding.createCompanionButton.setOnClickListener {
            showCompanionCreatorPopupAlert()
        }
    }

    private fun setObservers() {
        playerViewModel.players.observe(this) {
            playersAdapter.setData(it)
            it?.let {
                if(it.isEmpty()) showEmptyView() else hideEmptyView()
            } ?: run {
                showEmptyView()
            }
        }
    }

    private fun showEmptyView() {
        binding.noPlayersTextView.visibility = View.VISIBLE
        binding.companionsRecyclerview.visibility = View.GONE
    }

    private fun hideEmptyView() {
        binding.companionsRecyclerview.visibility = View.VISIBLE
        binding.noPlayersTextView.visibility = View.GONE
    }

    private fun showCompanionCreatorPopupAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        val alertdialogView = builder.create()
        val alertBinding = AlertGetNameBinding.inflate(layoutInflater)
        alertdialogView.setView(alertBinding.root)
        alertdialogView.window?.setBackgroundDrawableResource(R.color.transparent)

        alertBinding.alertEditTextTitle.text = getString(R.string.companion_name)
        alertBinding.alertTitle.text = getString(R.string.new_player_cta_content_description)

        alertBinding.registerButton.setOnClickListener {
            if (alertBinding.userNameEditText.text?.isNotBlank() == true) {
                playerViewModel.insertPlayer(alertBinding.userNameEditText.text.toString())
                alertdialogView.dismiss()
            }
        }

        alertdialogView.show()
    }

    override fun onPlayerClick(eventEntity: Player) {
        // Do something
    }
}