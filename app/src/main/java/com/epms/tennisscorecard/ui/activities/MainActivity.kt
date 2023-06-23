package com.epms.tennisscorecard.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.TennisScoreCardApp
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.databinding.ActivityMainBinding
import com.epms.tennisscorecard.databinding.AlertGetNameBinding
import com.epms.tennisscorecard.domain.models.toMatchPlayer
import com.epms.tennisscorecard.domain.models.toPlayer
import com.epms.tennisscorecard.ui.viewModels.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private var allPlayers: List<Player>? = null
    private var companionSelected: Player? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (TennisScoreCardApp.user == null) mainViewModel.getUser()

        mainViewModel.getPlayers()
        setUIListeners()
        setObservers()

    }

    private fun setUIListeners() {
        binding.startMatchButton.setOnClickListener {
            TennisScoreCardApp.user?.let { user ->
                companionSelected?.let { companion ->
                    val gson = Gson()
                    startActivity(
                        MatchActivity.newIntent(
                            this,
                            gson.toJson(user.toMatchPlayer()),
                            gson.toJson(companion)
                        )
                    )
                }
            }
        }
        binding.matchHistoryButton.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        binding.companionsButton.setOnClickListener {
            startActivity(Intent(this, PlayersActivity::class.java))
        }
    }

    private fun setObservers() {
        mainViewModel.players.observe(this) {
            allPlayers = it
            setupSpinner()
        }
        mainViewModel.user.observe(this) {
            it?.let {
                TennisScoreCardApp.user = it
            } ?: run {
                showUserRegisterPopupAlert()
            }
        }
    }

    private fun setupSpinner() {
        val aa = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            allPlayers?.map { it.name } ?: listOf()
        )
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(binding.companionSpinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@MainActivity
            prompt = "Select your companion"
            gravity = Gravity.CENTER

        }
    }


    private fun showUserRegisterPopupAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        val alertdialogView = builder.create()
        val alertBinding = AlertGetNameBinding.inflate(layoutInflater)
        alertdialogView.setView(alertBinding.root)
        alertdialogView.window?.setBackgroundDrawableResource(R.color.transparent)

        alertBinding.registerButton.setOnClickListener {
            if (alertBinding.userNameEditText.text?.isNotBlank() == true) {
                mainViewModel.setUser(alertBinding.userNameEditText.text.toString())
                alertdialogView.dismiss()
            }
        }

        alertdialogView.show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        allPlayers?.let { players ->
            if (players.size > position) {
                companionSelected = players[position]
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}
