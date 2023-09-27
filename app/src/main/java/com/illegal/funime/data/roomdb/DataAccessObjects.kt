package com.illegal.funime.data.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface AnimeDao{
    @Insert
    suspend fun insert(anime :AnimeFavourite)

    @Query("SELECT * FROM anime")
    suspend fun getAllAnime() : List<AnimeFavourite>

    @Delete
    suspend fun delete(anime : AnimeFavourite)

}

@Dao
interface MangaDao{
    @Upsert
    suspend fun insert(manga :MangaFavourite)

    @Query("SELECT * FROM manga")
    suspend fun getAllManga() : List<MangaFavourite>

    @Delete
    suspend fun delete(manga : MangaFavourite)

}

@Dao
interface SearchDao{
    @Upsert
    suspend fun insert(search :SearchHistory)

    @Query("SELECT * FROM search")
    suspend fun getAllSearch() : List<SearchHistory>

    @Delete
    suspend fun deleteSearch(search :SearchHistory)

}
