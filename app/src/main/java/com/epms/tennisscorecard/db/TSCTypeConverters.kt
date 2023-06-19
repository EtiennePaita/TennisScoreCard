package com.epms.tennisscorecard.db

import androidx.room.TypeConverter
import com.epms.tennisscorecard.models.Score
import com.google.gson.Gson

class TSCTypeConverters {
    @TypeConverter
    fun fromScoreToJSON(score: Score): String {
        return Gson().toJson(score)
    }
    @TypeConverter
    fun fromJSONToScore(json: String): Score {
        return Gson().fromJson(json, Score::class.java)
    }
}