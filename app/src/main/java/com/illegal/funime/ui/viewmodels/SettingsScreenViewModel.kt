package com.illegal.funime.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.illegal.funime.data.roomdb.RoomDataBase
import kotlinx.coroutines.launch

class SettingsScreenViewModel (private var application: Application) : AndroidViewModel(application) {

    private var roomInstance = RoomDataBase.getDatabase(application)

    fun deleteFavourites(){
        viewModelScope.launch {
            roomInstance.animeDao().deleteAllAnime()
            roomInstance.mangaDao().deleteAllManga()
        }
    }

}