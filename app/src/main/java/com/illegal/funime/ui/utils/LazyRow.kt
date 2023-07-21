package com.illegal.funime.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data

@Composable
fun LazyRowAnime(
    list :List<Data>
){
    LazyRow{
        items(count = list.size){Index ->
            Spacer(Modifier.width(10.dp))
            CardItem(imageUrl = list[Index].images.jpg.large_image_url, title = list[Index].title)
        }
    }
}