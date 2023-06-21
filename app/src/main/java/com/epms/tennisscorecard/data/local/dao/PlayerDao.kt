package com.epms.tennisscorecard.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM tsc_player")
    fun getPlayers(): Flow<List<PlayerEntity>?>

    @Query("SELECT * FROM tsc_player WHERE player_id == :id")
    fun getPlayerWithID(id: Int): Flow<PlayerEntity?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertPlayer(player: PlayerEntity)
}