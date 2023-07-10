package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.HeadAndMore
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.SpacerHeight
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.AnimeScreenViewModel


@Composable
fun AnimeScreen() {
    val viewModel: AnimeScreenViewModel = viewModel()
    Column {
        TopBar("It's anime time!")
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Pager()
                SpacerHeight(height = 10.dp)
                HeadAndMore(head = "Airing")
                if (viewModel.airingList != null) {
                    AiringRow(list = viewModel.airingList!!)
                }
            }
        }
    }
}

@Composable
fun AiringRow(
    list :List<Data>
){
    LazyRow{
        items(count = list.size){Index ->
            Spacer(Modifier.width(10.dp))
            CardItem(imageUrl = list[Index].images.jpg.large_image_url, title = list[Index].title)
        }
    }
}
