package com.illegal.funime.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.dataaccesscomponents.source.MangaDataSource
import com.illegal.funime.data.repositories.MangaRepository
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MangaScreenViewModel(
    private val mangaRepository: MangaRepository = MangaRepository(
        mangaApi = MainActivity.getMangaApiInstance()
    )
) : ViewModel() {

    val pager = Pager(
            config = PagingConfig(25)
        ) {
            MangaDataSource(mangaRepo = mangaRepository)
        }.flow.cachedIn(viewModelScope)

}
