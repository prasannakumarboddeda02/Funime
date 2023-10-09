package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.illegal.funime.data.roomdb.AnimeFavourite
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.ErrorMessage
import com.illegal.funime.ui.utils.ErrorMessageWithoutButton
import com.illegal.funime.ui.utils.ListLoadingBar
import com.illegal.funime.ui.utils.TopBarBack
import com.illegal.funime.ui.viewmodels.PaginationScreenViewModel

@Composable
fun MoreAnimeScreen(
    category :String,
    navController: NavController,
){
    val myViewModel: PaginationScreenViewModel = viewModel(factory = PaginationScreenViewModel.Factory(
        category = category
    ))
    val animeList = myViewModel.pager.collectAsLazyPagingItems()
    Column {
        TopBarBack(
            text = category,
            onClick = {
                navController.popBackStack()
            }
        )
        when(animeList.loadState.refresh){
            is LoadState.Loading -> {
                ListLoadingBar()
            }
            is LoadState.NotLoading -> {
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
                            animeList[it]?.let { anime ->
                                CardItem(
                                    imageUrl = anime.images.jpg.large_image_url,
                                    title = anime.title,
                                    id = anime.mal_id,
                                    onCardClick = {
                                        navController.navigate("animeDetail/${anime.mal_id}")
                                    }
                                )
                            }
                        }
                    }
                )
            }
            is LoadState.Error -> {
                ErrorMessageWithoutButton()
            }
        }
    }
}
