package com.illegal.funime.data.dataaccesscomponents.retrofit

import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetail
import com.illegal.funime.data.datamodels.retrofit.mangadetailmodel.MangaDetail
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaData
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaAPI {

    @GET("manga")
    suspend fun getMangaList(@Query("page") page :Int) :MangaResponse

    @GET("manga")
    suspend fun getMangaSearch(@Query("q") q:String) :MangaResponse

    @GET("manga/{id}")
    suspend fun getMangaById(
        @Path("id") id :Int
    ) : MangaDetail

}