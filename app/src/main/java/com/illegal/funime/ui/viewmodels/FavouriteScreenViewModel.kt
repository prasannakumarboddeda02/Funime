package com.illegal.funime.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.repositories.FavouritesRepository
import com.illegal.funime.data.roomdb.AnimeFavourite
import com.illegal.funime.data.roomdb.MangaFavourite
import com.illegal.funime.data.roomdb.RoomDataBase
import com.illegal.funime.data.roomdb.SearchHistory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavouritesRepository(
        animeDao = RoomDataBase.getDatabase(application).animeDao(),
        mangaDao = RoomDataBase.getDatabase(application).mangaDao(),
    )

    private val _animeState = MutableStateFlow<DataResult<List<AnimeFavourite>>>(DataResult.Loading)
    val animeState : StateFlow<DataResult<List<AnimeFavourite>>> = _animeState.asStateFlow()

    private val _mangaState = MutableStateFlow<DataResult<List<MangaFavourite>>>(DataResult.Loading)
    val mangaState : StateFlow<DataResult<List<MangaFavourite>>> = _mangaState.asStateFlow()


    fun getAnimeFavourites(){
        viewModelScope.launch {
            _animeState.value = DataResult.Success(repository.getAnimeFavourites())
        }
    }

    fun getMangaFavourites(){
        viewModelScope.launch {
            _mangaState.value = DataResult.Success(repository.getMangaFavourites())
        }
    }

}