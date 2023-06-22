package com.epms.tennisscorecard.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.domain.models.MatchRecap
import com.epms.tennisscorecard.domain.models.MatchState
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
    private val _matchEntities: MutableLiveData<List<MatchRecap>?> = MutableLiveData()
    val matchEntities: LiveData<List<MatchRecap>?> = _matchEntities

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

    fun insertMatch(matchState: MatchState, winningSets: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    matchRepository.insertMatch(matchState, winningSets)
                } catch (e: Exception) {
                    Log.e("MatchViewModel", "Insert error : $e")
                }
            }
        }
    }

}