package com.illegal.funime.data.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "manga")
data class MangaFavourite(
    @PrimaryKey(autoGenerate = true)
    val id :Long=0,
    val mangaId : String,
    val title :String,
    val imageUrl : String,
)
