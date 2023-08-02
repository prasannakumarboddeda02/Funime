package com.illegal.funime.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.repositories.AnimeRepository
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AnimeDetailScreenViewModel : ViewModel() {

    private val repository = AnimeRepository(
        retrofit = MainActivity.getAnimeApiInstance()
    )

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



}

sealed class AnimeDetailState{
    object Loading : AnimeDetailState()

    data class Success(
        val data : AnimeDetailData
    ) : AnimeDetailState()

    object Error : AnimeDetailState()
}