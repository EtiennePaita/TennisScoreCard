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


@Composable
fun mainActivityUIstart(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun functionPreview() {
    mainActivity(text = "Jean")
    // var text by remember { mutableStateOf("") }
}

@Composable
fun mainActivity(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(x = 0.dp, y = 0.dp)
            .background(color = Color(0x80000000))
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Code barre",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center, // Center vertically
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(x = 0.dp, y = 0.dp)
                    .shadow(elevation = 10.dp)
                    .background(color = Color(0xE5FFFFFF), shape = RoundedCornerShape(size = 10.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center, // Center vertically
                horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
            ) {
                Text(
                    text = "Tenniscore",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF161619),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "Winner text !",
                    fontSize = 13.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 40.dp)

                )

                Text(
                    text = "Play companion",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )

                TextField(
                    value = "name",
                    onValueChange = {
                        // TODO: make change
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                Text(
                    text = "Your name",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                )

                TextField(
                    value = "name",
                    onValueChange = {
                        // TODO: make change
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { /* Utilisez playerName pour le joueur */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(text = "Start")
                }
            }
        }
    }
}
