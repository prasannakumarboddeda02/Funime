package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.roomdb.SearchHistory
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.Loading
import com.illegal.funime.ui.utils.SearchListItem
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.SearchScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController :NavController
) {
    val searchScreenViewModel: SearchScreenViewModel = viewModel()
    searchScreenViewModel.getSearchHistory()
    var searchName by remember {
        mutableStateOf("")
    }

    var searching by remember {
        mutableStateOf(false)
    }
    val animeList = searchScreenViewModel.animeSearchList.collectAsState()
    val mangaList = searchScreenViewModel.mangaSearchList.collectAsState()
    val searchHistory = searchScreenViewModel.searchHistory.collectAsState().value

    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("anime","manga")

    Scaffold(
        topBar = {
            TopBar(
                title = "Search",
                onNavigationClick = {
                    navController.navigate("settings")
                })
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)) {
            TextField(
                value = searchName,
                onValueChange = {
                    searchName = it
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                },
                modifier = Modifier
                    .fillMaxWidth(6f)
                    .height(50.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(shape = RoundedCornerShape(50)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    searching = true
                    searchScreenViewModel.getAnimeSearch(name = searchName)
                    searchScreenViewModel.getMangaSearch(name = searchName)
                    searchScreenViewModel.addSearchItem(SearchHistory(searchName = searchName))
                })
            )
            if(searching) {
                TabRow(selectedTabIndex = state) {
                    titles.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = state == index,
                            onClick = { state = index }
                        )
                    }
                }
                if (state == 0) {
                    LazyVerticalGrid(
                        modifier = Modifier.padding(all = 5.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        columns = GridCells.Fixed(3),
                        content = {
                            items(
                                count = animeList.value.size,
                                key = { it }
                            ) {
                                animeList.value[it].let { anime ->
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
                        })
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier.padding(all = 5.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        columns = GridCells.Fixed(3),
                        content = {
                            items(
                                count = mangaList.value.size,
                                key = { it }
                            ) {
                                mangaList.value[it].let { manga ->
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
            }
            else{
                when(searchHistory){
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
                        Text(
                            text = "search history",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(all = 10.dp)
                        )
                        LazyColumn(
                            content = {
                                items(
                                    count = searchHistory.data.size,
                                    itemContent = {index ->
                                        SearchListItem(text = searchHistory.data[index].searchName)
                                    })
                            })
                    }

                    is DataResult.Error -> {

                    }
                }
            }
        }
    }
}
