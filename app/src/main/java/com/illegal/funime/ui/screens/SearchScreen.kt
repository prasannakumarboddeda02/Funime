package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.illegal.funime.ui.utils.TopBar

@Composable
fun SearchScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar("search!")
        Text(
            text = "Search screen",
            modifier = Modifier.fillMaxSize(),
        )
    }
}