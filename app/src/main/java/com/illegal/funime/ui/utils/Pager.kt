package com.illegal.funime.ui.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import com.illegal.funime.data.horizontalpagerdata.PagerSlideAnime
import com.illegal.funime.data.horizontalpagerdata.PagerSlideManga
import com.illegal.funime.ui.theme.RobotoSlab

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Pager(
    anime :Boolean
){
    val pagerState = rememberPagerState()
    val slidesAnime = PagerSlideAnime.values()
    val slidesManga = PagerSlideManga.values()
    Box {
        HorizontalPager(
            state = pagerState,
            pageCount = 4,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 10.dp)),
            contentPadding = PaddingValues(0.dp),
            pageSize = PageSize.Fill,
            beyondBoundsPageCount = 0,
            pageSpacing = 0.dp,
            verticalAlignment = Alignment.CenterVertically
        ) { index ->
            Box{
                Image(
                    painter = painterResource(id = if(anime) slidesAnime[index].image else slidesManga[index].image),
                    contentDescription = if(anime) slidesAnime[index].title else slidesManga[index].title
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
                        text = if(anime) slidesAnime[index].title else slidesManga[index].title,
                        color = Color.White,
                        fontFamily = RobotoSlab,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    }
                }
            }
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
