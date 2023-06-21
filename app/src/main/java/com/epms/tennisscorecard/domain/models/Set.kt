package com.epms.tennisscorecard.domain.models

/**
 * @property gameScore saves the number of games won on this set.
 * @property isWon defines if the set is won. Null if it is still in progress.
 */
data class Set(
    var gameScore: Int = 0,
    var isWon: Boolean? = null
)