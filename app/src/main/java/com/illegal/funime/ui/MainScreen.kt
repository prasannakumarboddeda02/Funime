package com.illegal.funime.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.illegal.funime.ui.navigation.NavigationHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavigationHost(
        navController = navController,
    )
}