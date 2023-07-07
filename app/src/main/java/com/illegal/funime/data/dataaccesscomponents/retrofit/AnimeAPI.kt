package com.illegal.funime.data.dataaccesscomponents.retrofit

import com.illegal.funime.data.datamodels.retrofit.animemodel.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeAPI {

    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("page") page:Int
    ): AnimeResponse

    @GET("seasons/upcoming")
    suspend fun getUpcomingSeason(
        @Query("page") page: Int
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int
    ): AnimeResponse

}