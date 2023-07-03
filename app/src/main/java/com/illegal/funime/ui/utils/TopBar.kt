package com.illegal.funime.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(
                text = "It's anime time",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        navigationIcon = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "drawer")
        }
    )
}