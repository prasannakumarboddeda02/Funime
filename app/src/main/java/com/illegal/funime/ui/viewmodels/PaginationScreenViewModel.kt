package com.illegal.funime.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.illegal.funime.data.dataaccesscomponents.source.AiringPaginationSource
import com.illegal.funime.data.dataaccesscomponents.source.FilterPaginationSource
import com.illegal.funime.data.dataaccesscomponents.source.PopularPaginationSource
import com.illegal.funime.data.dataaccesscomponents.source.UpcomingPaginationSource
import com.illegal.funime.data.repositories.AnimeRepository
import com.illegal.funime.ui.MainActivity

class PaginationScreenViewModel(
    private val animeRepository: AnimeRepository = AnimeRepository(
        retrofit = MainActivity.getAnimeApiInstance()
    ),
    private val category :String
) :ViewModel() {

    class Factory(private val category: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PaginationScreenViewModel(category = category) as T
        }
    }

    val pager = Pager(
        config = PagingConfig(15)
    ) {
        when (category) {
            "Airing" -> AiringPaginationSource(animeRepo = animeRepository)
            "Upcoming" -> UpcomingPaginationSource(animeRepo = animeRepository)
            "Top rated" -> PopularPaginationSource(animeRepo = animeRepository)
            "Popular" -> FilterPaginationSource(animeRepo = animeRepository)
            else -> FilterPaginationSource(animeRepo = animeRepository)
        }
    }.flow.cachedIn(viewModelScope)

}
