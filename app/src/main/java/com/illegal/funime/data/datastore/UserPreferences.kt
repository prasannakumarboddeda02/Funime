package com.illegal.funime.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val context : Context) {

    private val Context.dataStore by preferencesDataStore(
        name = "settings"
    )

    suspend fun storeTheme(theme : Int){
        context.dataStore.edit {
            it[THEME_KEY] = theme
        }
    }

    fun getTheme() = context.dataStore.data.map {
            it[THEME_KEY] ?: 0
        }

    companion object {
        val THEME_KEY = intPreferencesKey("theme")
    }

}