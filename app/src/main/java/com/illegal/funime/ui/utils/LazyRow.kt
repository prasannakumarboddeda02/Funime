package com.illegal.funime.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data

@Composable
fun LazyRowAnime(
    list :List<Data>,
    navController: NavController
){
    LazyRow{
        items(count = list.size){index ->
            Spacer(Modifier.width(10.dp))
            CardItem(
                imageUrl = list[index].images.jpg.large_image_url,
                title = list[index].title,
                id = list[index].mal_id,
                onCardClick = {
                    navController.navigate("animeDetail/${list[index].mal_id}")
                }
            )
        }
    }
}