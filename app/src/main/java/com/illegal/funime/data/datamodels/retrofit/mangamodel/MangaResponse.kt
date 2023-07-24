package com.illegal.funime.data.datamodels.retrofit.mangamodel

data class MangaResponse(
    val `data`: List<MangaData>,
    val pagination: Pagination
)