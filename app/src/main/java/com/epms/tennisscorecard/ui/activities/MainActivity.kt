package com.epms.tennisscorecard.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epms.tennisscorecard.domain.models.Match
import com.epms.tennisscorecard.domain.models.Player
import com.epms.tennisscorecard.R
import com.epms.tennisscorecard.databinding.ActivityMainBinding
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
import com.epms.tennisscorecard.data.local.entities.toPlayer
import com.epms.tennisscorecard.ui.viewModels.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var match: Match
    private lateinit var player1: Player
    private lateinit var player2: Player
    private var allPlayers: List<PlayerEntity>? = null
    private var matchHistory: List<MatchEntity>? = null
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getPlayers()
        mainViewModel.getAllMatches()
        setUpTestMatch()
        setUIListeners()
        setObservers()

    }

    private fun setUpTestMatch() {
        player1 = Player(1, "John Smith")
        player2 = Player(2, "Antoine De LaMarmotte")
        match = Match(player1, player2)
    }

    private fun setUIListeners() {
        binding.createPlayerButton.setOnClickListener {
            if (!binding.playerNameEditText.text.isNullOrBlank()) {
                mainViewModel.insertPlayer(PlayerEntity(binding.playerNameEditText.text.toString()))
                binding.playerNameEditText.text = null
            }
        }
        binding.goToPlayersButton.setOnClickListener {
            startActivity(Intent(this, PlayersActivity::class.java))
        }
        binding.startMatchButton.setOnClickListener {
            allPlayers?.let { players ->
                if(players.size < 2) return@setOnClickListener
                val gson = Gson()
                val p1 = gson.toJson(players[0].toPlayer())
                val p2 = gson.toJson(players[1].toPlayer())
                startActivity(MatchActivity.newIntent(this, p1, p2))
            }
        }
    }

    private fun setObservers() {
        mainViewModel.players.observe(this) {
            allPlayers = it
            binding.playerCounterText.text = "${getString(R.string.players_registered)} : ${it?.size ?: 0}"
        }
        mainViewModel.matches.observe(this) {
            matchHistory = it
            binding.matchCounterText.text = "Match played : ${it?.size ?: 0}"
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
