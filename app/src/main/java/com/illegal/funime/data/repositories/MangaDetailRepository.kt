package com.illegal.funime.data.repositories

import com.illegal.funime.data.dataaccesscomponents.retrofit.MangaAPI
import com.illegal.funime.data.datamodels.retrofit.mangadetailmodel.MangaDetailData
import com.illegal.funime.data.roomdb.MangaDao
import com.illegal.funime.data.roomdb.MangaFavourite

class MangaDetailRepository(
    private val mangaDao: MangaDao,
    val retrofit: MangaAPI,
) {


    suspend fun insertMangaDatabase(
        mangaFavourite: MangaFavourite
    ){
        mangaDao.insert(manga = mangaFavourite)
    }

    suspend fun getFavourites() : List<MangaFavourite>{
        return mangaDao.getAllManga()
    }

    suspend fun deleteMangaFavourite(
        mangaFavourite : MangaFavourite
    ){
        mangaDao.delete(manga = mangaFavourite)
    }

    suspend fun getMangaById(
        id : Int
    ) : MangaDetailData {
        return retrofit.getMangaById(id = id).data
    }

}