package com.illegal.funime.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Drawer(){
    Text(
        text = "Heading",
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
}