package com.epms.tennisscorecard.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.domain.models.MatchRecap
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.domain.repositories.MatchRepository
import com.epms.tennisscorecard.domain.repositories.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val matchRepository: MatchRepository
) : ViewModel() {
    private val _players: MutableLiveData<List<Player>?> = MutableLiveData()
    val players: LiveData<List<Player>?> = _players

    private val _matches: MutableLiveData<List<MatchRecap>?> = MutableLiveData()
    val matches: LiveData<List<MatchRecap>?> = _matches

    fun getPlayers() {
        viewModelScope.launch {
            try {
                playerRepository.getPlayers()?.collect {
                    Log.d("MainViewModel", "Players $it")
                    _players.value = it
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Get error : $e")
            }
        }
    }

    fun insertPlayer(playerName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    playerRepository.insertPlayer(playerName)
                } catch (e: Exception) {
                    Log.e("MainViewModel", "Insert error : $e")
                }
            }
        }
    }

    fun getAllMatches() {
        viewModelScope.launch {
            try {
                matchRepository.getMatchHistory()?.collect {
                    Log.d("MainViewModel", "Matches $it")
                    _matches.value = it
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Get error : $e")
            }
        }
    }
}