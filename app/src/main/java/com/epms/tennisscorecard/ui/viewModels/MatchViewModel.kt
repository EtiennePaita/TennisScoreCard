package com.epms.tennisscorecard.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.data.local.entities.MatchEntity
import com.epms.tennisscorecard.domain.repositories.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val matchRepository: MatchRepository
) : ViewModel() {
    private val _matchEntities: MutableLiveData<List<MatchEntity>?> = MutableLiveData()
    val matchEntities: LiveData<List<MatchEntity>?> = _matchEntities

    fun getMatchHistory(){
        viewModelScope.launch {
            try {
                matchRepository.getMatchHistory()?.collect {
                    _matchEntities.value = it
                }
            } catch (e: Exception) {
                Log.e("MatchViewModel", "Error on get match history : $e")
            }
        }
    }

    fun insertMatch(match: MatchEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    matchRepository.insertMatch(match)
                } catch (e: Exception) {
                    Log.e("MatchViewModel", "Insert error : $e")
                }
            }
        }
    }

}