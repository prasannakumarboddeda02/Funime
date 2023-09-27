package com.illegal.funime.data.repositories

import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.dataaccesscomponents.retrofit.MangaAPI
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import com.illegal.funime.data.datamodels.retrofit.mangamodel.MangaResponse
import com.illegal.funime.data.roomdb.SearchDao
import com.illegal.funime.data.roomdb.SearchHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class SearchRepository(
    private val searchDao: SearchDao,
    private val animeAPI: AnimeAPI,
    private val mangaAPI: MangaAPI
){

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

    suspend fun getSearchHistory() : List<SearchHistory>{
        return searchDao.getAllSearch()
    }

    private suspend fun addSearchItem(search : SearchHistory) {
        searchDao.insert(search = search)
    }

    suspend fun checkAndAddSearch(
        list : List<SearchHistory>,
        search : String
    ){
        var flag = true
        for(i in list){
            if(i.searchName == search){
                flag = false
                break
            }
        }
        if(flag){
            addSearchItem(search = SearchHistory(searchName = search))
        }
    }

    suspend fun deleteSearch(search :SearchHistory){
        searchDao.deleteSearch(search = search)
    }
}