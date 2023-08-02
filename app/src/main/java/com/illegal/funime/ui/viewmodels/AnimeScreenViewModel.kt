package com.illegal.funime.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.repositories.AnimeRepository
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class AnimeScreenViewModel : ViewModel() {

    private val retrofitInstance = MainActivity.getAnimeApiInstance()

    private val animeRepository = AnimeRepository(retrofit = retrofitInstance)

    private var _state = MutableStateFlow<AnimeScreenState>(AnimeScreenState.Loading)
    var state : StateFlow<AnimeScreenState> = _state.asStateFlow()

    var airingList by mutableStateOf<List<Data>?>(null)

    var upcomingList by mutableStateOf<List<Data>?>(null)

    var popularList by mutableStateOf<List<Data>?>(null)

    var popularListFilter by mutableStateOf<List<Data>?>(null)

    init{
        try {
            getAiringAnime()
            getUpcomingAnime()
            getPopularAnime()
            getPopularAnimeTopRated()
        }
        catch(e : Exception){
            _state.value = AnimeScreenState.Error(e)
        }
    }

    private fun getAiringAnime(){
        viewModelScope.launch {
            animeRepository.getAiringList()
                .flowOn(context = Dispatchers.IO)
                .collect{
                    airingList = it
                }
        }
    }

    private fun getUpcomingAnime(){
        viewModelScope.launch {
            upcomingList = animeRepository.getUpcomingList()
        }
    }

    private fun getPopularAnime(){
        viewModelScope.launch {
            popularList = animeRepository.getPopularList()
            _state.value = AnimeScreenState.Success
        }
    }

    private fun getPopularAnimeTopRated(){
        viewModelScope.launch {
            delay(3000)
            popularListFilter = animeRepository.getPopularListFilter(filter = "bypopularity")
        }
    }
}

sealed class AnimeScreenState{

    object Loading : AnimeScreenState()

    object Success : AnimeScreenState()

    data class Error(
        var error : Exception
    ) : AnimeScreenState()
}