package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.illegal.funime.data.DataResult
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.ErrorMessage
import com.illegal.funime.ui.utils.Loading
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.FavouriteScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    navController: NavController
){

    val viewModel : FavouriteScreenViewModel = viewModel()
    viewModel.getAnimeFavourites()
    viewModel.getMangaFavourites()
    val animeList = viewModel.animeState.collectAsState().value
    val mangaList = viewModel.mangaState.collectAsState().value

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("anime","manga")


    Scaffold(
        topBar = {
            TopBar(
                title = "Favourites",
                onNavigationClick = {
                    navController.navigate("settings")
                })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues = paddingValues)) {
            TabRow(selectedTabIndex = state) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = state == index,
                        onClick = { state = index }
                    )
                }
            }
            if(state == 0){
                when(animeList){
                    is DataResult.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Loading(modifier = Modifier.fillMaxWidth())
                        }
                    }

                    is DataResult.Success -> {
                        if(animeList.data.isEmpty()){
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "No favourites")
                            }
                        }
                        LazyVerticalGrid(
                            modifier = Modifier.padding(all = 5.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            columns = GridCells.Fixed(3),
                            content = {
                                items(
                                    count = animeList.data.size,
                                    key = { it }
                                ) {
                                    animeList.data[it].let { anime ->
                                        CardItem(
                                            imageUrl = anime.imageUrl,
                                            title = anime.title,
                                            id = Integer.parseInt(anime.animeId),
                                            onCardClick = {
                                                navController.navigate("animeDetail/${animeList.data[it].animeId}")
                                            }
                                        )
                                    }
                                }
                            })
                    }

                    is DataResult.Error -> {
                            ErrorMessage()
                        }
                }
            }
            else{
                when(mangaList){
                    is DataResult.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Loading(modifier = Modifier.fillMaxWidth())
                        }
                    }

                    is DataResult.Success -> {
                        if(mangaList.data.isEmpty()){
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "No favourites")
                            }
                        }
                        LazyVerticalGrid(
                            modifier = Modifier.padding(all = 5.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            columns = GridCells.Fixed(3),
                            content = {
                                items(
                                    count = mangaList.data.size,
                                    key = { it }
                                ) {
                                    mangaList.data[it].let { manga ->
                                        CardItem(
                                            imageUrl = manga.imageUrl,
                                            title = manga.title,
                                            id = Integer.parseInt(manga.mangaId),
                                            onCardClick = {
                                                navController.navigate("mangaDetail/${manga.mangaId}")
                                            }
                                        )
                                    }
                                }
                            })
                    }

                    is DataResult.Error -> {
                        ErrorMessage()
                    }
                }
            }
        }
    }
}