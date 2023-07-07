package com.illegal.funime.ui.screens

import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.illegal.funime.ui.MainActivity
import com.illegal.funime.ui.utils.Pager
import com.illegal.funime.ui.utils.TopBar
import kotlinx.coroutines.runBlocking


@Composable
fun AnimeScreen(){
Column(modifier = Modifier.fillMaxSize()) {
    TopBar("It's anime time!")
    LazyColumn{
        item{
            Pager()
        }
    }
}
}