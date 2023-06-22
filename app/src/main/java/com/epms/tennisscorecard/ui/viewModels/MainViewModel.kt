package com.epms.tennisscorecard.ui.viewModels

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
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
