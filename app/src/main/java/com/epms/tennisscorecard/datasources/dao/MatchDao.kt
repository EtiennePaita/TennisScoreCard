package com.epms.tennisscorecard.datasources.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epms.tennisscorecard.models.MatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM tsc_match")
    fun getMatchHistory(): Flow<List<MatchEntity>?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertMatch(match: MatchEntity)
}