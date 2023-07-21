package com.illegal.funime.data.dataaccesscomponents.retrofit

import com.illegal.funime.data.datamodels.retrofit.mangamodel.Data
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaAPI {

    @GET("/manga")
    suspend fun getMangaList(@Query("page") page :Int) :MangaResponse

}