package com.illegal.funime.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.datastore.UserPreferences
import com.illegal.funime.data.roomdb.AnimeFavourite
import com.illegal.funime.data.roomdb.RoomDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserPreferenceViewModel(private var application: Application) : AndroidViewModel(application) {

    private val dataStore = UserPreferences(application)


    init{
        getTheme()
    }

    private var _theme = MutableStateFlow(0)
    val theme : StateFlow<Int> = _theme.asStateFlow()

    private fun getTheme(){
        viewModelScope.launch {
            dataStore.getTheme().collectLatest {
                _theme.value = it
                Log.d("theme",theme.toString())
            }
        }
    }

    fun storeTheme(theme : Int){
        viewModelScope.launch {
            dataStore.storeTheme(theme)
            dataStore.getTheme()
        }
    }

}