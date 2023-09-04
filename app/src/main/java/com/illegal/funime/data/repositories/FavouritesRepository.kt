package com.illegal.funime.data.repositories

import com.illegal.funime.data.roomdb.AnimeDao
import com.illegal.funime.data.roomdb.AnimeFavourite
import com.illegal.funime.data.roomdb.MangaDao
import com.illegal.funime.data.roomdb.MangaFavourite
import com.illegal.funime.data.roomdb.SearchDao
import com.illegal.funime.data.roomdb.SearchHistory

class FavouritesRepository(
    private val animeDao: AnimeDao,
    private val mangaDao: MangaDao,
) {

    suspend fun getAnimeFavourites() : List<AnimeFavourite>{
        return animeDao.getAllAnime()
    }

    suspend fun getMangaFavourites() : List<MangaFavourite>{
        return mangaDao.getAllManga()
    }

}