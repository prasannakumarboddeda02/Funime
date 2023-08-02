package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.Drawer
import com.illegal.funime.ui.utils.HeadAndMore
import com.illegal.funime.ui.utils.LazyRowAnime
import com.illegal.funime.ui.utils.ListLoadingBar
import com.illegal.funime.ui.utils.Loading
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.SpacerHeight
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.AnimeScreenState
import com.illegal.funime.ui.viewmodels.AnimeScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun AnimeScreen(
    navController: NavController
) {
    val viewModel: AnimeScreenViewModel = viewModel()
    val state = viewModel.state.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                title = "It's anime time!",
                onNavigationClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        drawerContent = {
            Drawer()
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
                    Pager(anime = true)
                    SpacerHeight(height = 10.dp)
                    when(state.value){
                        is AnimeScreenState.Success -> {
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
                            if (viewModel.airingList != null) {
                                LazyRowAnime(
                                    list = viewModel.airingList!!,
                                    navController = navController)
                            }
                            else{
                                Loading(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                )
                            }
                            SpacerHeight(height = 10.dp)
                            HeadAndMore(
                                head = "Upcoming",
                                onMoreClick = {
                                    navController.navigate("more/Upcoming")
                                })
                            if(viewModel.upcomingList != null){
                                LazyRowAnime(list = viewModel.upcomingList!!,
                                    navController = navController)
                            }
                            else{
                                Loading(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                )
                            }
                            SpacerHeight(height = 10.dp)
                            HeadAndMore(
                                head = "Top rated",
                                onMoreClick = {
                                    navController.navigate("more/Top rated")
                                })
                            if(viewModel.popularList != null){
                                LazyRowAnime(list = viewModel.popularList!!,
                                    navController = navController)
                            }
                            else{
                                Loading(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                )
                            }
                            SpacerHeight(height = 10.dp)
                            HeadAndMore(
                                head = "Popular",
                                onMoreClick = {
                                    navController.navigate("more/popular")
                                })
                            if(viewModel.popularListFilter != null){
                                LazyRowAnime(list = viewModel.popularListFilter!!,
                                    navController = navController)
                            }
                            else{
                                Loading(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                )
                            }
                        }
                        is AnimeScreenState.Loading -> {
                            Spacer(modifier = Modifier.height(100.dp))
                            ListLoadingBar()
                        }
                        is AnimeScreenState.Error -> {
                            Text(
                                text = "Error"
                            )
                        }
                    }
                }
            }
        }
        }
}

