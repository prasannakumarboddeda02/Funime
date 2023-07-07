package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.illegal.funime.ui.utils.TopBar

@Composable
fun MangaScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar("It's manga time!")
        Text(
            text = "Manga screen",
            modifier = Modifier.fillMaxSize(),
        )
    }
}