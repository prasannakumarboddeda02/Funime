package com.illegal.funime.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.mangadetailmodel.MangaDetailData
import com.illegal.funime.data.repositories.MangaDetailRepository
import com.illegal.funime.data.roomdb.MangaFavourite
import com.illegal.funime.data.roomdb.RoomDataBase
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MangaDetailScreenViewModel (application: Application) : AndroidViewModel(application) {

    private var repository : MangaDetailRepository = MangaDetailRepository(
        retrofit = MainActivity.getMangaApiInstance(),
        mangaDao = RoomDataBase.getDatabase(application).mangaDao())

    private var _state = MutableStateFlow<DataResult<MangaDetailData>>(DataResult.Loading)
    var state : StateFlow<DataResult<MangaDetailData>> = _state.asStateFlow()

    private var _mangaFavourites  : List<MangaFavourite> = emptyList()

    private var _favouriteState = MutableStateFlow(false)
    var favouriteState : StateFlow<Boolean> = _favouriteState.asStateFlow()


    fun getMangaById(id : Int){
        viewModelScope.launch {
            try {
                _state.value =  DataResult.Success(
                    data = repository.getMangaById(id = id)
                )
                Log.d("detail:",_state.value.toString())
            } catch(e : HttpException){
                Log.d("Error",e.toString())
            }
            catch (e : Exception){
                _state.value = DataResult.Error(e = e)
            }
        }
    }


    fun saveMangaFavourites(
        mangaFavourite: MangaFavourite
    ){
        viewModelScope.launch {
            repository.insertMangaDatabase(mangaFavourite = mangaFavourite)
            getFavourites()
            _favouriteState.value = true
        }
    }

    fun getFavourites() {
        viewModelScope.launch {
            _mangaFavourites = repository.getFavourites()
        }
    }

    fun deleteMangaFavourite(
        id : String
    ){
        viewModelScope.launch{
            for(i in _mangaFavourites.indices){
                if(_mangaFavourites[i].mangaId == id){
                    repository.deleteMangaFavourite(_mangaFavourites[i])
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
        for(i in _mangaFavourites.indices){
            if(_mangaFavourites[i].mangaId == id){
                flag = true
                break
            }
        }
        _favouriteState.value = flag
    }


}