package com.illegal.funime.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.repositories.AnimeRepository
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.launch

class AnimeScreenViewModel : ViewModel() {

    private val retrofitInstance = MainActivity.getAnimeApiInstance()

    private val animeRepository = AnimeRepository(retrofit = retrofitInstance)

    var airingList by mutableStateOf<List<Data>?>(null)

    init{
        getAiringAnime()
    }

    private fun getAiringAnime(){
        viewModelScope.launch {
            airingList = animeRepository.getAiringList()
        }
    }


}