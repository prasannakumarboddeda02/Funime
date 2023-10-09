package com.illegal.funime.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
import com.illegal.funime.data.datamodels.retrofit.charactermodel.CharacterResponse
import com.illegal.funime.data.roomdb.AnimeFavourite
import com.illegal.funime.ui.utils.AnimeInformationCard
import com.illegal.funime.ui.utils.CardItem
import com.illegal.funime.ui.utils.Description
import com.illegal.funime.ui.utils.ErrorMessage
import com.illegal.funime.ui.utils.FavouriteIcon
import com.illegal.funime.ui.utils.Loading
import com.illegal.funime.ui.utils.ScoreCard
import com.illegal.funime.ui.utils.TopBarBack
import com.illegal.funime.ui.viewmodels.AnimeDetailScreenViewModel
import com.illegal.funime.ui.viewmodels.AnimeDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    id :String,
    navController: NavController,
) {
    val viewModel : AnimeDetailScreenViewModel = viewModel()
    val state = viewModel.state.collectAsState().value
    val characterList = viewModel.characterList.collectAsState().value
    viewModel.getAnimeById(id = id.toInt())
    viewModel.getCharactersByAnime(id = id.toInt())
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
                if(favourite){
                    viewModel.deleteAnimeFavourite(id = id)
                }
                else {
                    viewModel.saveAnimeFavourites(
                        animeFavourite = AnimeFavourite(
                            animeId = id,
                            title = (state as AnimeDetailState.Success).data.title,
                            imageUrl = state.data.images.jpg.large_image_url
                        )
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            when (state) {
                is AnimeDetailState.Loading -> {
                    Loading(modifier = Modifier.fillMaxSize())
                }

                is AnimeDetailState.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            DetailImage(
                                data = state.data
                            )
                            Spacer(modifier = Modifier.height(18.dp))
                            ScoreCard(
                                rating = state.data.score.toString(),
                                people = state.data.members.toString(),
                                status = state.data.status
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            AnimeInformationCard(
                                type = state.data.type,
                                episodes = state.data.episodes,
                                season = state.data.season,
                                year = state.data.year,
                                rating = state.data.rating,
                                duration = state.data.duration
                            )
                            Description(
                                synopsis = state.data.synopsis
                            )
                            CastAndCharacters(
                                dataResult = characterList
                            )
                        }
                    }
                }

                is AnimeDetailState.Error -> {
                    ErrorMessage(
                        onClick = {
                            viewModel.reload(id = id.toInt())
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun DetailImage(
    data : AnimeDetailData?
){
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
                    .padding(horizontal = 20.dp))
        }
        if (data != null) {
            Text(
                text = data.title_japanese
            )
        }
    }
}


@Composable
fun CastAndCharacters(
    dataResult : DataResult<List<CharacterResponse>>
){
    when(dataResult){
        is DataResult.Loading -> {
            Loading(modifier = Modifier)
        }

        is DataResult.Success -> {
            LazyRow{
                items(
                    count = dataResult.data.size){index ->
                    Log.d("size:",dataResult.data.size.toString())
                    CardItem(
                        imageUrl = dataResult.data[index].data.images.jpg.image_url,
                        title = dataResult.data[index].data.name,
                        id = dataResult.data[index].data.mal_id) {
                    }
                }
            }
        }

        is DataResult.Error -> {

        }
    }
}