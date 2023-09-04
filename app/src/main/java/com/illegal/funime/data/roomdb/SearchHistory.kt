package com.illegal.funime.data.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search")
data class SearchHistory(
    @PrimaryKey(autoGenerate = true)
    val id :Long = 0,
    val searchName : String
)
