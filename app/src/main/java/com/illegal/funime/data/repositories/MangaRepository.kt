package com.illegal.funime.data.repositories

import com.illegal.funime.data.dataaccesscomponents.retrofit.MangaAPI
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaResponse

class MangaRepository(
    private val mangaApi : MangaAPI
) {

    suspend fun getMangaList(
        page: Int
    ): MangaResponse {
        return mangaApi.getMangaList(page = page)
    }

}