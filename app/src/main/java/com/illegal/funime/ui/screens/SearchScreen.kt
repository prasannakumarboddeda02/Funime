package com.illegal.funime.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.illegal.funime.ui.utils.Searchbar
import com.illegal.funime.ui.utils.TopBar
import com.illegal.funime.ui.viewmodels.SearchScreenViewModel

@Composable
fun SearchScreen(){
    val searchScreenViewModel :SearchScreenViewModel = viewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar("search!")
        Searchbar(
            onSearch = {
                searchScreenViewModel.getAnimeSearch()
                searchScreenViewModel.getMangaSearch()
            }
        )
    }
}