package com.illegal.funime.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.illegal.funime.ui.navigation.NavigationHost
import com.illegal.funime.ui.viewmodels.UserPreferenceViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    userPreferences : UserPreferenceViewModel
) {
    val navController = rememberNavController()
    NavigationHost(
        navController = navController,
        userPreferences = userPreferences
    )
}