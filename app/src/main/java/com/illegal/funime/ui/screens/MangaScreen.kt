package com.illegal.funime.ui.screens

import android.util.Log
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.MangaScreenViewModel

@Composable
fun MangaScreen(
    paddingValues :PaddingValues
) {
    val mangaScreenViewModel: MangaScreenViewModel = viewModel()
    val mangaList = mangaScreenViewModel.pager.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
    ) {
        TopBar("It's manga time!")
        Pager(anime = false)
        LazyVerticalGrid(
            modifier = Modifier.padding(all = 5.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            columns = GridCells.Fixed(3),
            content = {
                items(
                    count = mangaList.itemCount,
                    key = { it }
                ) {
                    mangaList[it]?.let {manga ->
                        CardItem(
                            imageUrl = manga.images.jpg.large_image_url,
                            title = manga.title
                        )
                    }
                }
                item {
                    when (mangaList.loadState.append) {
                        is LoadState.NotLoading -> Unit
                        is LoadState.Loading -> ListLoadingBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        )

                        is LoadState.Error -> {

                        }

                        else -> {}
                    }
                }
            })
    }
}

@Composable
fun ListLoadingBar(modifier :Modifier){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp),
            strokeWidth = 5.dp
        )
    }
}

