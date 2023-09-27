package com.illegal.funime.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
import com.illegal.funime.data.datamodels.retrofit.charactermodel.CharacterResponse
import com.illegal.funime.data.repositories.AnimeDetailRepository
import com.illegal.funime.data.roomdb.AnimeFavourite
import com.illegal.funime.data.roomdb.RoomDataBase
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AnimeDetailScreenViewModel (application: Application) : AndroidViewModel(application) {


    private var repository :AnimeDetailRepository = AnimeDetailRepository(
        retrofit = MainActivity.getAnimeApiInstance(),
        animeDao = RoomDataBase.getDatabase(application).animeDao())

    private var _state = MutableStateFlow<AnimeDetailState>(AnimeDetailState.Loading)
    var state : StateFlow<AnimeDetailState> = _state.asStateFlow()

    private var _characterList = MutableStateFlow<DataResult<List<CharacterResponse>>>(DataResult.Loading)
    var characterList : StateFlow<DataResult<List<CharacterResponse>>> = _characterList.asStateFlow()

    private var _animeFavourites  : List<AnimeFavourite> = emptyList()

    private var _favouriteState = MutableStateFlow(false)
    var favouriteState : StateFlow<Boolean> = _favouriteState.asStateFlow()


    fun getAnimeById(id : Int){
        viewModelScope.launch {
            try {
                _state.value =  AnimeDetailState.Success(
                    data = repository.getAnimeById(id = id)
                )
            } catch(e :HttpException){
                Log.d("Error",e.toString())
            }
            catch (e : Exception){
                _state.value = AnimeDetailState.Error
            }
        }
    }

    fun getCharactersByAnime(id :Int){
        viewModelScope.launch {
            try {
                Log.d("Character:",repository.getCharacterByAnime(id = id).toString())
                _characterList.value = DataResult.Success(repository.getCharacterByAnime(id = id))
            }
            catch(e :Exception){
                _characterList.value = DataResult.Error(e = e)
            }
        }
    }

    fun saveAnimeFavourites(
        animeFavourite: AnimeFavourite
    ){
        viewModelScope.launch {
            repository.insertAnimeDatabase(animeFavourite = animeFavourite)
            getFavourites()
            _favouriteState.value = true
        }
    }

    fun getFavourites() {
        viewModelScope.launch {
            _animeFavourites = repository.getFavourites()
        }
    }

    fun deleteAnimeFavourite(
        id : String
    ){
        viewModelScope.launch{
            for(i in _animeFavourites.indices){
                if(_animeFavourites[i].animeId == id){
                    repository.deleteAnimeFavourite(_animeFavourites[i])
                    getFavourites()
                    break
                }
            }
            _favouriteState.value = false
        }
    }

    fun checkFavourite(
        id : String
    ) {
        var flag = false
        for(i in _animeFavourites.indices){
            if(_animeFavourites[i].animeId == id){
                flag = true
                break
            }
        }
        _favouriteState.value = flag
    }
}

sealed class AnimeDetailState{
    data object Loading : AnimeDetailState()

    data class Success(
        val data : AnimeDetailData
    ) : AnimeDetailState()

    data object Error : AnimeDetailState()
}


