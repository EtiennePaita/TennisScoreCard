package com.epms.tennisscorecard.repositories

import com.epms.tennisscorecard.TennisScoreCardApp
import com.epms.tennisscorecard.models.PlayerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayerRepository @Inject constructor() {
    private val playerDao = TennisScoreCardApp.matchDB?.PlayerDao()

    fun getPlayers(): Flow<List<PlayerEntity>?>? {
        return playerDao?.getPlayers()
    }

    fun getPlayerWithID(id: Int): Flow<PlayerEntity?>? {
        return playerDao?.getPlayerWithID(id)
    }

    fun insertPlayer(player: PlayerEntity) {
        playerDao?.insertPlayer(player)
    }
}