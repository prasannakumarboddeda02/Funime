package com.illegal.funime.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBack(
    text :String,
    onClick :() -> Unit
){
    TopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
        title = {
            Text(
                text = text,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        navigationIcon = {
            Box(
                modifier = Modifier
                    .clickable {onClick()}
                    .clip(shape = RoundedCornerShape(size = 0.5f))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back arrow",
                    modifier = Modifier
                        .padding(all = 10.dp)
                )
            }
        },
    )
}