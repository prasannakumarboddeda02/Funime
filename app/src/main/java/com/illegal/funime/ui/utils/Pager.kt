package com.illegal.funime.ui.utils

import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.illegal.funime.data.horizontalpagerdata.PagerSlideAnime
import com.illegal.funime.data.horizontalpagerdata.PagerSlideManga
import com.illegal.funime.ui.theme.RobotoSlab

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    anime :Boolean,
    navController : NavController
){
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // provide pageCount
        4
    }
    val slidesAnime = PagerSlideAnime.values()
    val slidesManga = PagerSlideManga.values()
    Box {
        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 10.dp)),
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = null,
            pageNestedScrollConnection = PagerDefaults.pageNestedScrollConnection(
                Orientation.Horizontal
            ),
            pageContent = {index ->
                Box(
                    modifier = Modifier
                        .clickable {
                            if(anime)
                                navController.navigate("animeDetail/${slidesAnime[index].id}")
                            else
                                navController.navigate("mangaDetail/${slidesManga[index].id}")
                        }
                ) {
                    Image(
                        painter = painterResource(id = if (anime) slidesAnime[index].image else slidesManga[index].image),
                        contentDescription = if (anime) slidesAnime[index].title else slidesManga[index].title
                    )
                    Box(
                        Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Black.copy(0.6f)
                                    )
                                )
                            )
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(all = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextWithShadow(
                            text = if (anime) slidesAnime[index].title else slidesManga[index].title,
                            color = Color.White,
                            fontFamily = RobotoSlab,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        )
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(4) {
                Box(
                    Modifier
                        .size(8.dp)
                        .padding(if (pagerState.currentPage % 4 == it) 0.dp else 1.dp)
                        .background(
                            color = if (pagerState.currentPage % 4 == it)
                                Color.White.copy(0.9f)
                            else Color.White.copy(0.6f),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}
