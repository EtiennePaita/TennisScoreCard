package com.epms.tennisscorecard.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epms.tennisscorecard.domain.models.MatchRecap
import com.epms.tennisscorecard.domain.repositories.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val matchRepository: MatchRepository
): ViewModel() {
    private val _matches: MutableLiveData<List<MatchRecap>?> = MutableLiveData()
    val matches: LiveData<List<MatchRecap>?> = _matches

    fun getAllMatches() {
        viewModelScope.launch {
            try {
                matchRepository.getMatchHistory()?.collect {
                    Log.d("MainViewModel", "Matches $it")
                    _matches.value = it
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Get error : $e")
            }
        }
    }
}