package com.epms.tennisscorecard.data.local.datasources

import android.content.Context
import android.content.SharedPreferences

class PreferencesDataSource(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    companion object {
        private const val preferenceName = "com.epms"
        private const val userIdKey = "user_id"
        private const val userNameKey = "user_name"
    }

    fun getUserId(): String? {
        return prefs.getString(userIdKey, null)
    }
    fun setUserId(userId: String){
        editor.putString(userIdKey, userId).apply()
    }

    fun getUserName(): String? {
        return prefs.getString(userNameKey, null)
    }
    fun setUserName(userName: String){
        editor.putString(userNameKey, userName).apply()
    }

    fun clearPreferences(){
        editor.clear().apply()
    }
}