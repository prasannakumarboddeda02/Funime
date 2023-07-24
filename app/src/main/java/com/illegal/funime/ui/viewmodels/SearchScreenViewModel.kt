package com.illegal.funime.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaData
import com.illegal.funime.data.repositories.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val searchRepository: SearchRepository = SearchRepository()
) : ViewModel() {


    private var _animeSearchList = MutableStateFlow<List<Data>>(emptyList())
    var animeSearchList : StateFlow<List<Data>> = _animeSearchList.asStateFlow()

    private var _mangaSearchList = MutableStateFlow<List<MangaData>>(emptyList())
    var mangaSearchList : StateFlow<List<MangaData>> = _mangaSearchList.asStateFlow()

    fun getAnimeSearch(
        name :String
    ){
        viewModelScope.launch {
            searchRepository.getAnimeSearch(name = name).collect {
                _animeSearchList.value = it
            }
        }
    }

    fun getMangaSearch(
        name :String
    ){
        viewModelScope.launch{
            searchRepository.getMangaSearch(name = name).collect{
                _mangaSearchList.value = it.data
            }
        }
    }
}
