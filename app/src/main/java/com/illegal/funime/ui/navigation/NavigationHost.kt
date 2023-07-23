package com.illegal.funime.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.illegal.funime.ui.screens.AnimeScreen
import com.illegal.funime.ui.screens.FavouritesScreen
import com.illegal.funime.ui.screens.MangaScreen
import com.illegal.funime.ui.screens.MoreAnimeScreen
import com.illegal.funime.ui.screens.SearchScreen
import com.illegal.funime.ui.utils.BottomNavItem


@Composable
fun NavigationHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Anime.screen_route) {
        composable(BottomNavItem.Anime.screen_route) {
            AnimeScreen(
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable(BottomNavItem.Manga.screen_route) {
            MangaScreen(paddingValues = paddingValues)
        }
        composable(BottomNavItem.Favourites.screen_route) {
            FavouritesScreen()
        }
        composable(BottomNavItem.Search.screen_route) {
            SearchScreen()
        }
        composable(
            "more/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            it.arguments?.getString("category")?.let { it1 ->
                MoreAnimeScreen(
                    it1
                )
            }
        }
    }
}