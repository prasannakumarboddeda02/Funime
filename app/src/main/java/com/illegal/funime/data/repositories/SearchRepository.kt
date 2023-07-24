package com.illegal.funime.data.repositories

import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaResponse
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchRepository{

    private val animeAPI = MainActivity.getAnimeApiInstance()

    private val mangaAPI = MainActivity.getMangaApiInstance()

    suspend fun getAnimeSearch(
        name :String
    ) : Flow<List<Data>> = flow{
        emit(animeAPI.getAnimeSearch(q = name).data)
    }

    suspend fun getMangaSearch(
        name :String
    ) :Flow<MangaResponse> = flow{
        emit(mangaAPI.getMangaSearch(q = name))
    }
}