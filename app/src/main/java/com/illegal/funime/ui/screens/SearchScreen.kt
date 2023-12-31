package com.illegal.funime.ui.screens

import androidx.compose.foundation.background
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
import com.illegal.funime.ui.utils.BottomNavigationBar
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.ErrorMessage
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

    val searchState = searchScreenViewModel.state.collectAsState().value

    searchScreenViewModel.getSearchHistory()

    var searchName by remember {
        mutableStateOf("")
    }

    var searching by remember {
        mutableStateOf(false)
    }
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
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
                        .height(70.dp)
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                        .clip(shape = RoundedCornerShape(50)),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        searching = true
                        searchScreenViewModel.getSearch(name = searchName)
                        searchScreenViewModel.addSearchItem(search = searchName)
                    })
                )
            }
                when (searchState) {
                    is DataResult.Loading -> {
                        Loading(modifier = Modifier.fillMaxSize())
                    }

                    is DataResult.Success -> {
                        if (searchState.data.searching) {
                            TabRow(
                                selectedTabIndex = state,
                                containerColor = MaterialTheme.colorScheme.secondary
                            ) {
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
                                            count = searchState.data.animeList.size,
                                            key = { it }
                                        ) {
                                            searchState.data.animeList[it].let { anime ->
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
                                            count = searchState.data.mangaList.size,
                                            key = { it }
                                        ) {
                                            searchState.data.mangaList[it].let { manga ->
                                                CardItem(
                                                    imageUrl = manga.images.jpg.large_image_url,
                                                    title = manga.title,
                                                    id = manga.mal_id,
                                                    onCardClick = {
                                                        navController.navigate("mangaDetail/${manga.mal_id}")
                                                    }
                                                )
                                            }
                                        }
                                    })
                            }
                        }

                    else {
                        when (searchHistory) {
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
                                if (searchHistory.data.isEmpty()) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "No history"
                                        )
                                    }
                                }
                                LazyColumn(
                                    content = {
                                        items(
                                            count = searchHistory.data.size,
                                            itemContent = { index ->
                                                SearchListItem(
                                                    text = searchHistory.data[index].searchName,
                                                    onItemClick = {
                                                        searching = true
                                                        searchScreenViewModel.getSearch(name = searchHistory.data[index].searchName)
                                                    },
                                                    onCloseClick = {
                                                        searchScreenViewModel.deleteSearch(
                                                            searchHistory.data[index]
                                                        )
                                                    })
                                            })
                                    })
                            }

                            is DataResult.Error -> {
                                ErrorMessage(
                                    onClick = {
                                        searchScreenViewModel.getSearchHistory()
                                    }
                                )
                            }
                        }
                    }
                }

                    is DataResult.Error -> {
                        ErrorMessage(
                            onClick = {
                                searching = true
                                searchScreenViewModel.getSearch(name = searchName)
                                searchScreenViewModel.addSearchItem(search = searchName)
                            }
                        )
                    }
                }
        }
    }
}


