package com.epms.tennisscorecard.domain.repositories

import com.epms.tennisscorecard.TennisScoreCardApp
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
import com.epms.tennisscorecard.data.local.entities.toPlayer
import com.epms.tennisscorecard.domain.models.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayerRepository @Inject constructor() {
    private val playerDao = TennisScoreCardApp.matchDB?.PlayerDao()

    fun getPlayers(): Flow<List<Player>?>? {
        return playerDao?.getPlayers()?.map { players -> players?.map { it.toPlayer() } }
    }

    fun getPlayerWithID(id: Int): Flow<Player?>? {
        return playerDao?.getPlayerWithID(id)?.map { it?.toPlayer() }
    }

    fun insertPlayer(playerName: String) {
        playerDao?.insertPlayer(PlayerEntity(playerName))
    }
}