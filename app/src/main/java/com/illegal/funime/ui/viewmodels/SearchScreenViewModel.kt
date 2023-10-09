package com.illegal.funime.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaData
import com.illegal.funime.data.repositories.SearchRepository
import com.illegal.funime.data.roomdb.RoomDataBase
import com.illegal.funime.data.roomdb.SearchHistory
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = SearchRepository(
        animeAPI = MainActivity.getAnimeApiInstance(),
        mangaAPI = MainActivity.getMangaApiInstance(),
        searchDao = RoomDataBase.getDatabase(application).searchDao()
    )

    private var _state = MutableStateFlow<DataResult<ListData>>(DataResult.Success(
        ListData(
            animeList = emptyList(),
            mangaList = emptyList(),
            searching = false
        )
    ))
    var state : StateFlow<DataResult<ListData>> = _state.asStateFlow()

    private var _searchHistory =  MutableStateFlow<DataResult<List<SearchHistory>>>(DataResult.Loading)
    var searchHistory : StateFlow<DataResult<List<SearchHistory>>> = _searchHistory.asStateFlow()

    fun getSearch(
        name :String
    ){
        try {
            viewModelScope.launch {
                _state.value = repository.getSearch(name = name)
            }
        }
        catch(e : Exception){
            _state.value = DataResult.Error(e = e)
        }
    }

    fun getSearchHistory(){
        viewModelScope.launch {
            _searchHistory.value = DataResult.Success(repository.getSearchHistory())
        }
    }

    fun addSearchItem(search : String){
        viewModelScope.launch {
            checkAndAddSearchItem(search = search)
        }
    }

    private suspend fun checkAndAddSearchItem(search : String){
        searchHistory.collect{dataResult ->
            when(dataResult){
                is DataResult.Success ->{
                    repository.checkAndAddSearch(
                        list = dataResult.data,
                        search = search
                    )
                }
                else -> {
                }
            }
        }
    }

    fun deleteSearch(search :SearchHistory){
        viewModelScope.launch {
            repository.deleteSearch(search = search)
            getSearchHistory()
        }
    }
}

data class ListData(
    var animeList : List<Data>,
    var mangaList : List<MangaData>,
    var searching : Boolean
)
