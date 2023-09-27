package com.illegal.funime.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavouriteIcon(
    state : Boolean,
    onClick : () -> Unit
){

    Box(
        modifier = Modifier
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }
            .clip(shape = CircleShape)
            .background(color = Color.LightGray)
    ) {
        Icon(
            imageVector = if (state) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "fav button",
            modifier = Modifier
                .padding(all = 10.dp),
            tint = Color.Red
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewFavourite(){
    FavouriteIcon(
        state = true,
        onClick = {})
}