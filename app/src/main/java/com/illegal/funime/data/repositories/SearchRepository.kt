package com.illegal.funime.data.repositories

import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.ui.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchRepository{

    private val animeAPI = MainActivity.getAnimeApiInstance()

    suspend fun getAnimeSearch(
        name :String
    ) : Flow<List<Data>> = flow{
        emit(animeAPI.getAnimeSearch(q = name).data)
    }

    suspend fun getMangaSearch(){

    }
}