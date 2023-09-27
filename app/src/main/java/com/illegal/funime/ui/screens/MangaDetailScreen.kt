package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.mangadetailmodel.MangaDetailData
import com.illegal.funime.data.roomdb.MangaFavourite
import com.illegal.funime.ui.utils.Description
import com.illegal.funime.ui.utils.FavouriteIcon
import com.illegal.funime.ui.utils.Loading
import com.illegal.funime.ui.utils.ScoreCard
import com.illegal.funime.ui.utils.TopBarBack
import com.illegal.funime.ui.viewmodels.MangaDetailScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MangaDetailScreen(
    id :String,
    navController: NavController
) {

    val viewModel: MangaDetailScreenViewModel = viewModel()
    val state = viewModel.state.collectAsState().value
    viewModel.getMangaById(id = id.toInt())
    viewModel.getFavourites()
    viewModel.checkFavourite(id = id)

    val favourite = viewModel.favouriteState.collectAsState().value

    Scaffold(
        topBar = {
            TopBarBack(text = "",
                onClick = {
                    navController.popBackStack()
                })
        },
        floatingActionButton = {
            FavouriteIcon(state = favourite) {
                if (favourite) {
                    viewModel.deleteMangaFavourite(id = id)
                } else {
                    when(state) {
                        is DataResult.Success -> {
                            viewModel.saveMangaFavourites(
                                mangaFavourite = MangaFavourite(
                                    mangaId = id,
                                    title = state.data.title,
                                    imageUrl = state.data.images.jpg.large_image_url
                                )
                            )
                        }

                        else -> {}
                    }
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            when (state) {
                is DataResult.Loading -> {
                    Loading(modifier = Modifier.fillMaxSize())
                }

                is DataResult.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            MangaDetailImage(
                                data = state.data
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ScoreCard(
                                rating = state.data.score.toString(),
                                people = state.data.members.toString(),
                                status = state.data.status
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            /*AnimeInformationCard(
                            type = state.data.type,
                            episodes = state.data.episodes,
                            season = state.data.season,
                            year = state.data.year,
                            rating = state.data.rating,
                            duration = state.data.duration
                        )*/
                            Description(
                                synopsis = state.data.synopsis
                            )
                        }
                    }
                }

                is DataResult.Error -> {
                    Text("Error")
                }

            }


        }


    }

}

@Composable
fun MangaDetailImage(
    data: MangaDetailData?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (data != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.images.jpg.large_image_url).build(),
                contentDescription = data.title,
                contentScale = ContentScale.Crop,
                filterQuality = FilterQuality.Low,
                modifier = Modifier
                    .width(200.dp)
                    .aspectRatio(3f / 4f)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
        if (data != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = data.title,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
        }
        if (data != null) {
            Text(
                text = data.title_japanese
            )
        }
    }
}