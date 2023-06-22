package com.epms.tennisscorecard.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.epms.tennisscorecard.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM tsc_user")
    fun getUser(): Flow<List<UserEntity>?>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun setUser(userEntity: UserEntity)
}