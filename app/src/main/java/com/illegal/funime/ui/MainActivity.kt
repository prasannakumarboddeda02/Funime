package com.illegal.funime.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.dataaccesscomponents.retrofit.MangaAPI
import com.illegal.funime.ui.theme.FunimeTheme
import com.illegal.funime.ui.viewmodels.UserPreferenceViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userPreferencesVM : UserPreferenceViewModel = viewModel()
            val theme = userPreferencesVM.theme.collectAsStateWithLifecycle()
            FunimeTheme(
                darkTheme = when(theme.value){
                    1  -> false
                    2 -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MainScreen(
                        userPreferences = userPreferencesVM,
                    )
                }
            }
        }
    }

    companion object{

        private const val baseUrl = "https://api.jikan.moe/v4/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getAnimeApiInstance(): AnimeAPI{
                return retrofit.create(AnimeAPI::class.java)
        }

        fun getMangaApiInstance(): MangaAPI{
            return retrofit.create(MangaAPI::class.java)
        }
    }
}
