package com.illegal.funime.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.TopBarBack
import com.illegal.funime.ui.viewmodels.PaginationScreenViewModel

@Composable
fun MoreAnimeScreen(
    category :String
){
    val myViewModel: PaginationScreenViewModel = viewModel(factory = PaginationScreenViewModel.Factory(
        category = category
    )
    )
    val animeList = myViewModel.pager.collectAsLazyPagingItems()
    Column {
        TopBarBack(text = category)
        LazyVerticalGrid(
            modifier = Modifier.padding(all = 5.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            columns = GridCells.Fixed(3),
            content = {
                items(
                    count = animeList.itemCount,
                    key = { it }
                ){
                    animeList[it]?.let { manga ->
                        CardItem(
                            imageUrl = manga.images.jpg.large_image_url,
                            title = manga.title
                        )
                    }
                }
            }
        )
    }
}
