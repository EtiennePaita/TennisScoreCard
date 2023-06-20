package com.epms.tennisscorecard.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.models.MatchEntity
import com.epms.tennisscorecard.models.PlayerEntity
import com.epms.tennisscorecard.repositories.MatchRepository
import com.epms.tennisscorecard.repositories.PlayerRepository
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
    private val _players: MutableLiveData<List<PlayerEntity>?> = MutableLiveData()
    val players: LiveData<List<PlayerEntity>?> = _players

    private val _matches: MutableLiveData<List<MatchEntity>?> = MutableLiveData()
    val matches: LiveData<List<MatchEntity>?> = _matches

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

    fun insertPlayer(player: PlayerEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    playerRepository.insertPlayer(player)
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