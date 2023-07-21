package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.illegal.funime.ui.utils.HeadAndMore
import com.illegal.funime.ui.utils.LazyRowAnime
import com.illegal.funime.ui.utils.Loading
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.SpacerHeight
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.AnimeScreenViewModel


@Composable
fun AnimeScreen(
    paddingValues: PaddingValues
) {
    val viewModel: AnimeScreenViewModel = viewModel()
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        TopBar("It's anime time!")
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Pager(anime = true)
                SpacerHeight(height = 10.dp)
                HeadAndMore(head = "Airing")
                if (viewModel.airingList != null) {
                    LazyRowAnime(list = viewModel.airingList!!)
                }
                else{
                    Loading(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                    )
                }
                SpacerHeight(height = 10.dp)
                HeadAndMore(head = "Upcoming")
                if(viewModel.upcomingList != null){
                    LazyRowAnime(list = viewModel.upcomingList!!)
                }
                else{
                    Loading(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                    )
                }
                SpacerHeight(height = 10.dp)
                HeadAndMore(head = "Top rated")
                if(viewModel.popularList != null){
                    LazyRowAnime(list = viewModel.popularList!!)
                }
                else{
                    Loading(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                    )
                }
                SpacerHeight(height = 10.dp)
                HeadAndMore(head = "Popular")
                if(viewModel.popularListFilter != null){
                    LazyRowAnime(list = viewModel.popularListFilter!!)
                }
                else{
                    Loading(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                    )
                }
            }
        }
    }
}

