package com.illegal.funime.data.roomdb

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.illegal.funime.ui.MainActivity
import com.illegal.funime.ui.utils.BottomNavItem

@Database(entities = [AnimeFavourite::class,MangaFavourite::class,SearchHistory::class], version = 1, exportSchema = false)
abstract class RoomDataBase : RoomDatabase(){

    abstract fun animeDao() : AnimeDao

    abstract fun mangaDao() : MangaDao

    abstract fun searchDao() : SearchDao

    companion object {
        @Volatile
        var INSTANCE: RoomDataBase? = null
        fun getDatabase(context: Context): RoomDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}