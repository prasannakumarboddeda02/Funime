package com.illegal.funime.data.repositories

import android.util.Log
import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
import com.illegal.funime.data.roomdb.AnimeDao
import com.illegal.funime.data.roomdb.AnimeFavourite

class AnimeDetailRepository(
    val animeDao: AnimeDao,
    val retrofit: AnimeAPI,
) {

    suspend fun insertAnimeDatabase(
        animeFavourite: AnimeFavourite
    ){
        Log.d("anime", animeFavourite.toString())
        animeDao.insert(anime = animeFavourite)
    }

    suspend fun getAnimeById(
        id : Int
    ) : AnimeDetailData {
        return retrofit.getAnimeById(id = id).data
    }
}