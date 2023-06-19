package com.epms.tennisscorecard.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.models.PlayerEntity
import com.epms.tennisscorecard.repositories.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {
    private val _players: MutableLiveData<List<PlayerEntity>> = MutableLiveData()
    val players: LiveData<List<PlayerEntity>?> = _players

    fun getPlayers() {
        viewModelScope.launch {
            try {
                playerRepository.getPlayers()?.collect {
                    _players.value = it
                }
            } catch (e: Exception) {
                Log.e("PlayerViewModel", "Get error : $e")
            }
        }
    }

    fun insertPlayer(player: PlayerEntity) {
        try {
            playerRepository.insertPlayer(player)
        } catch (e: Exception) {
            Log.e("PlayerViewModel", "Insert error : $e")
        }
    }
}