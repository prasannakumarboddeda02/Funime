package com.illegal.funime.data.repositories

import android.util.Log
import com.illegal.funime.data.DataResult
import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
import com.illegal.funime.data.datamodels.retrofit.animemodel.AnimeResponse
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.roomdb.AnimeDao
import com.illegal.funime.data.roomdb.AnimeFavourite
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AnimeRepository(
    val retrofit: AnimeAPI,
) {
    suspend fun getAllList() : DataResult<ArrayList<List<Data>>> {
        val list = ArrayList<List<Data>>()
        return try {
            list.add(retrofit.getCurrentSeason(page = 1).data)
            list.add(retrofit.getUpcomingSeason(page = 1).data)
            list.add(retrofit.getPopularAnime(page = 1).data)
            delay(1000)
            list.add(retrofit.getPopularAnimeFilter(page = 1, filter = "bypopularity").data)
            DataResult.Success(data = list)
        }
        catch(e :Exception){
            DataResult.Error(e = e)
        }

    }
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

    suspend fun getAiringPaginationList(page :Int) : AnimeResponse{
        return retrofit.getCurrentSeason(page = page)
    }

    suspend fun getUpcomingPaginationList(page :Int) : AnimeResponse{
        return retrofit.getUpcomingSeason(page = page)
    }

    suspend fun getPopularPaginationList(page :Int) : AnimeResponse{
        return retrofit.getPopularAnime(page = page)
    }

    suspend fun getPopularFilterPaginationList(page :Int) : AnimeResponse{
        return retrofit.getPopularAnimeFilter(page = page, filter = "bypopularity")
    }

    suspend fun getAnimeById(
        id : Int
    ) : AnimeDetailData {
        return retrofit.getAnimeById(id = id).data
    }

}