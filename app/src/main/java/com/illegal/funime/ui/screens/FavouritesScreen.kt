package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopBar(
                title = "Favourites",
                onNavigationClick = {})
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)) {
            Text(
                text = "Favourites screen",
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}