package com.illegal.funime.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.illegal.funime.ui.navigation.NavigationHost
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    var paddingValues :PaddingValues
    val navController = rememberNavController()
    Scaffold(
        content = {
            NavigationHost(
                navController = navController,
                paddingValues = it
            )
        },
        bottomBar = { BottomNavigationBar(navController = navController) }
    )
}