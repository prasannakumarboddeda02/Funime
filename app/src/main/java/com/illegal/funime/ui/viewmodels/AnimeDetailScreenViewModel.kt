package com.illegal.funime.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
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

    fun saveAnimeFavourites(
        animeFavourite: AnimeFavourite
    ){
        viewModelScope.launch {
            repository.insertAnimeDatabase(animeFavourite = animeFavourite)
        }
    }


}

sealed class AnimeDetailState{
    data object Loading : AnimeDetailState()

    data class Success(
        val data : AnimeDetailData
    ) : AnimeDetailState()

    data object Error : AnimeDetailState()
}


