package com.illegal.funime.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
    Log.d( "pk" ,mangaList.itemCount.toString())
    Column(
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        TopBar("It's manga time!")
        Pager(anime = false)
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            content = {
                items(
                    count = mangaList.itemCount,
                    key = { it }
                ) {
                    mangaList[it]?.let {manga ->
                        CardItem(
                            imageUrl = manga.images.jpg.large_image_url,
                            title = manga.title_english
                        )
                    }
                }
            })
    }
}

