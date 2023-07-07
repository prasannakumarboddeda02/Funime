package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.illegal.funime.ui.utils.TopBar

@Composable
fun FavouritesScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar("Favourites")
        Text(
            text = "Favourites screen",
            modifier = Modifier.fillMaxSize(),
        )
    }
}