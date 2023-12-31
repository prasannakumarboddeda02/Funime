package com.illegal.funime.data.dataaccesscomponents.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.repositories.AnimeRepository
import kotlinx.coroutines.delay

class PopularPaginationSource(
    private val animeRepo : AnimeRepository
) : PagingSource<Int, Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition/*?.let{position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1)?:page?.nextKey?.plus(1)
        }*/
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try{
            val currentPage = params.key?: 1
            val response = animeRepo.getPopularPaginationList(currentPage)
            delay(
                if (currentPage == 1) {
                    0
                } else {
                    500
                }
            )
            val previousPage =
                if (currentPage == 1) {
                    null
                } else {
                    currentPage - 1
                }
            val nextPage =
                if (response.pagination.has_next_page) {
                    currentPage + 1
                } else {
                    null
                }
            LoadResult.Page(
                data = response.data,
                prevKey = previousPage,
                nextKey = nextPage
            )
        }
        catch(e :Exception){
            LoadResult.Error(e)
        }
    }
}