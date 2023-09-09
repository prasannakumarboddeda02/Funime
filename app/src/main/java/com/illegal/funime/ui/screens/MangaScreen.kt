package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.illegal.funime.ui.theme.RobotoSlab
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.ListLoadingBar
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.MangaScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaScreen(
    navController: NavController
) {
    val mangaScreenViewModel: MangaScreenViewModel = viewModel()
    val mangaList = mangaScreenViewModel.pager.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            TopBar(
                title = "It's manga time!",
                onNavigationClick = {
                    navController.navigate("settings")
                })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
        ) {
            Pager(
                anime = false,
                navController = navController)
            when (mangaList.loadState.refresh) {
                is LoadState.Loading -> {
                    ListLoadingBar()
                }

                is LoadState.NotLoading -> {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Popular manga",
                        fontSize = 18.sp,
                        fontFamily = RobotoSlab,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
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
                                mangaList[it]?.let { manga ->
                                    CardItem(
                                        imageUrl = manga.images.jpg.large_image_url,
                                        title = manga.title,
                                        id = manga.mal_id,
                                        onCardClick = {}
                                    )
                                }
                            }
                        })
                }

                is LoadState.Error -> {
                    TODO(reason = "Implement Error")
                }
            }
        }
    }
}


