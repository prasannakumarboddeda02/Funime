package com.illegal.funime.data.repositories

import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AnimeRepository(
    val retrofit: AnimeAPI
) {
    suspend fun getAiringList() : Flow<List<Data>> = flow{
        emit(retrofit.getCurrentSeason(page = 1).data)
    }

    suspend fun getUpcomingList() : List<Data>{
        return retrofit.getUpcomingSeason(page = 1).data
    }

    suspend fun getPopularList() : List<Data>{
        return retrofit.getPopularAnime(page = 1).data
    }

    suspend fun getPopularListFilter(filter :String) :List<Data>{
        return retrofit.getPopularAnimeFilter(page = 1, filter = filter).data
    }

}