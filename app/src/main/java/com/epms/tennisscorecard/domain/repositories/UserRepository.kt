package com.epms.tennisscorecard.domain.repositories

import com.epms.tennisscorecard.TennisScoreCardApp
import com.epms.tennisscorecard.data.local.entities.UserEntity
import com.epms.tennisscorecard.data.local.entities.toUser
import com.epms.tennisscorecard.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepository @Inject constructor() {
    private val userDao = TennisScoreCardApp.matchDB?.UserDao()

    fun getUser(): Flow<User?>? {
        return userDao?.getUser()?.map { it?.firstOrNull()?.toUser() }
    }

    fun setUser(userName: String) {
        userDao?.setUser(UserEntity(userName))
    }
}