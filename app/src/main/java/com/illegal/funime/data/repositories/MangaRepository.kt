package com.illegal.funime.data.repositories

import android.util.Log
import com.illegal.funime.data.dataaccesscomponents.retrofit.MangaAPI
import com.illegal.funime.data.datamodels.retrofit.mangamodel.Data
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaResponse

class MangaRepository(
    private val mangaApi : MangaAPI
) {

    suspend fun getMangaList(
        page :Int
    ) :MangaResponse {
        val response = mangaApi.getMangaList(page = page)
        Log.d("pk:", response.data.toString())
        return response
    }

}