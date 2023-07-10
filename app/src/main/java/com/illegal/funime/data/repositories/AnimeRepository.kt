package com.illegal.funime.data.repositories

import com.illegal.funime.data.dataaccesscomponents.retrofit.AnimeAPI
import com.illegal.funime.data.datamodels.retrofit.animemodel.Data
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

class AnimeRepository(
    val retrofit: AnimeAPI
) {

    suspend fun getAiringList() : List<Data>{
        return retrofit.getCurrentSeason(page = 1).data
    }

}