package com.illegal.funime.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.repositories.SearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchScreenViewModel(
    val searchRepository: SearchRepository = SearchRepository()
) : ViewModel() {

    private var _animeSearchList = MutableStateFlow<List<Data>>(emptyList())
    var animeSearchList : StateFlow<List<Data>> = _animeSearchList.asStateFlow()

    private var _mangaSearchList = MutableStateFlow<List<Data>>(emptyList())
    var mangaSearchList : StateFlow<List<Data>> = _mangaSearchList.asStateFlow()

    fun getAnimeSearch(){

    }

    fun getMangaSearch(){

    }
}
