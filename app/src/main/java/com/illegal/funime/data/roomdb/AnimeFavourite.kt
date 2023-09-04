package com.illegal.funime.data.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeFavourite(
@PrimaryKey(autoGenerate = true)
val id :Long=0,
val animeId : String,
val title :String,
val imageUrl : String,
)

