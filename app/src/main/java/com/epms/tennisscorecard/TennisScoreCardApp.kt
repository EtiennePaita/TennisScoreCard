package com.epms.tennisscorecard

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.epms.tennisscorecard.db.TSCDatabase
import com.epms.tennisscorecard.domain.models.User
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TennisScoreCardApp: Application() {
    companion object {
        var matchDB : TSCDatabase? = null
        var user: User? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        matchDB = TSCDatabase.invoke(this)
    }
}