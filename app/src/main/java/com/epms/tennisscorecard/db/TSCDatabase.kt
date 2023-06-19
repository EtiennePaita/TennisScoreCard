package com.epms.tennisscorecard.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.epms.tennisscorecard.datasources.dao.MatchDao
import com.epms.tennisscorecard.datasources.dao.PlayerDao
import com.epms.tennisscorecard.models.MatchEntity
import com.epms.tennisscorecard.models.PlayerEntity

@TypeConverters(value = [TSCTypeConverters::class])
@Database(entities = [MatchEntity::class, PlayerEntity::class], version = 1,exportSchema = true)
abstract class TSCDatabase: RoomDatabase() {
    abstract fun MatchDao(): MatchDao
    abstract fun PlayerDao(): PlayerDao

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