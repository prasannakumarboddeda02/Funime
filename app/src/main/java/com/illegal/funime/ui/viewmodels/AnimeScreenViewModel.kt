package com.illegal.funime.ui.viewmodels

import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.repositories.AnimeRepository
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeScreenViewModel : ViewModel() {

    private val retrofitInstance = MainActivity.getAnimeApiInstance()

    private val animeRepository = AnimeRepository(retrofit = retrofitInstance)


    private var _state = MutableStateFlow<DataResult<ArrayList<List<Data>>>>(DataResult.Loading)
    var state : StateFlow<DataResult<ArrayList<List<Data>>>> = _state.asStateFlow()


    init{
        try {
            getAllList()
        }
        catch(e : Exception){
            _state.value = DataResult.Error(e = e)
        }
    }

    private fun getAllList(){
        viewModelScope.launch {
            _state.value = animeRepository.getAllList()
        }
    }

    fun reload(){
        try {
            _state.value = DataResult.Loading
            getAllList()
        }
        catch(e : Exception){
            _state.value = DataResult.Error(e = e)
        }

    }
}
