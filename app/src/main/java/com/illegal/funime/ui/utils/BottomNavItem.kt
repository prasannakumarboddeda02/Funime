package com.illegal.funime.ui.utils

import com.illegal.funime.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Anime : BottomNavItem("Anime",R.drawable.anime,"animeScreen")
    object Manga: BottomNavItem("Manga", R.drawable.manga,"mangaScreen")
    object Favourites: BottomNavItem("Favourites",R.drawable.favorite,"favouritesScreen")
    object Search: BottomNavItem("Search",R.drawable.search,"SearchScreen")
}