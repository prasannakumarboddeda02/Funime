package com.illegal.funime.data.dataaccesscomponents.retrofit

import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetail
import com.illegal.funime.data.datamodels.retrofit.animemodel.AnimeResponse
import com.illegal.funime.data.datamodels.retrofit.charactermodel.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeAPI {

    @GET("seasons/now")
    suspend fun getCurrentSeason(
        @Query("page") page:Int,
    ): AnimeResponse

    @GET("seasons/upcoming")
    suspend fun getUpcomingSeason(
        @Query("page") page: Int
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getPopularAnime(
        @Query("page") page: Int,
    ): AnimeResponse

    @GET("top/anime")
    suspend fun getPopularAnimeFilter(
        @Query("page") page: Int,
        @Query("filter") filter: String
    ): AnimeResponse

    @GET("anime")
    suspend fun getAnimeSearch(
        @Query("q") q: String,
        //@Query("page") page: Int
    ): AnimeResponse

    @GET("anime/{id}")
    suspend fun getAnimeById(
        @Path("id") id :Int
    ) : AnimeDetail

    @GET("anime/{id}/characters")
    suspend fun getAnimeCharactersByAnimeID(
        @Path("id") id: Int
    ): List<CharacterResponse>
}