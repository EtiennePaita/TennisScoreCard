package com.epms.tennisscorecard.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.epms.tennisscorecard.data.local.dao.MatchDao
import com.epms.tennisscorecard.data.local.dao.PlayerDao
import com.epms.tennisscorecard.data.local.dao.UserDao
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.data.local.entities.PlayerEntity
import com.epms.tennisscorecard.data.local.entities.UserEntity

@TypeConverters(value = [TSCTypeConverters::class])
@Database(entities = [MatchEntity::class, PlayerEntity::class, UserEntity::class], version = 1,exportSchema = true)
abstract class TSCDatabase: RoomDatabase() {
    abstract fun MatchDao(): MatchDao
    abstract fun PlayerDao(): PlayerDao
    abstract fun UserDao(): UserDao

    companion object {
        @Volatile private var instance: TSCDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            TSCDatabase::class.java, "tsc_room.db")
            .build()
    }
}