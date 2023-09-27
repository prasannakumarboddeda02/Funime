package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.ErrorMessage
import com.illegal.funime.ui.utils.HeadAndMore
import com.illegal.funime.ui.utils.LazyRowAnime
import com.illegal.funime.ui.utils.ListLoadingBar
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.SpacerHeight
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.AnimeScreenViewModel
import kotlinx.coroutines.launch
import java.lang.Error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreen(
    navController: NavController
) {
    val viewModel: AnimeScreenViewModel = viewModel()
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopBar(
                title = "It's anime time!",
                onNavigationClick = {
                    navController.navigate("settings")
                })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
    ) {
        Column(
            modifier = Modifier
                .padding(paddingValues = it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Pager(
                        anime = true,
                        navController = navController)
                    SpacerHeight(height = 10.dp)
                    when(state.value){
                        is DataResult.Success -> {
                            HeadAndMore(
                                head = "Airing",
                                onMoreClick = {
                                    navController.navigate("more/Airing"){
                                        popUpTo(id = navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
                                }
                            )
                            LazyRowAnime(
                                list = (state.value as DataResult.Success<ArrayList<List<Data>>>).data[0],
                                navController = navController)
                            SpacerHeight(height = 10.dp)
                            HeadAndMore(
                                head = "Upcoming",
                                onMoreClick = {
                                    navController.navigate("more/Upcoming")
                                })
                            LazyRowAnime(list = (state.value as DataResult.Success<ArrayList<List<Data>>>).data[1],
                                navController = navController)
                            SpacerHeight(height = 10.dp)
                            HeadAndMore(
                                head = "Top rated",
                                onMoreClick = {
                                    navController.navigate("more/Top rated")
                                })
                            LazyRowAnime(list = (state.value as DataResult.Success<ArrayList<List<Data>>>).data[2],
                                navController = navController)
                            SpacerHeight(height = 10.dp)
                            HeadAndMore(
                                head = "Popular",
                                onMoreClick = {
                                    navController.navigate("more/popular")
                                })
                            LazyRowAnime(list = (state.value as DataResult.Success<ArrayList<List<Data>>>).data[3],
                                navController = navController)
                        }
                        is DataResult.Loading -> {
                            Spacer(modifier = Modifier.height(100.dp))
                            ListLoadingBar()
                        }
                        is DataResult.Error -> {
                            Spacer(modifier = Modifier.height(100.dp))
                            ErrorMessage()
                        }
                    }
                }
            }
        }
    }
}

