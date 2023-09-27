package com.illegal.funime.data.repositories

import android.util.Log
import androidx.room.RoomDatabase
import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.datamodels.retrofit.animedetailmodel.AnimeDetailData
import com.illegal.funime.data.datamodels.retrofit.charactermodel.CharacterResponse
import com.illegal.funime.data.roomdb.AnimeDao
import com.illegal.funime.data.roomdb.AnimeFavourite

class AnimeDetailRepository(
    val animeDao: AnimeDao,
    val retrofit: AnimeAPI,
) {

    suspend fun insertAnimeDatabase(
        animeFavourite: AnimeFavourite
    ){
        animeDao.insert(anime = animeFavourite)
    }

    suspend fun getAnimeById(
        id : Int
    ) : AnimeDetailData {
        return retrofit.getAnimeById(id = id).data
    }

    suspend fun getCharacterByAnime(
        id: Int
    ) : List<CharacterResponse>{
        return retrofit.getAnimeCharactersByAnimeID(id = id)
    }

    suspend fun getFavourites() : List<AnimeFavourite>{
        return animeDao.getAllAnime()
    }

    suspend fun deleteAnimeFavourite(
        animeFavourite : AnimeFavourite
    ){
        animeDao.delete(anime = animeFavourite)
    }
}