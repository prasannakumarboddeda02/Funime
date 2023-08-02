package com.illegal.funime.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.illegal.funime.ui.screens.AnimeDetailScreen
import com.illegal.funime.ui.screens.AnimeScreen
import com.illegal.funime.ui.screens.FavouritesScreen
import com.illegal.funime.ui.screens.MangaScreen
import com.illegal.funime.ui.screens.MoreAnimeScreen
import com.illegal.funime.ui.screens.SearchScreen
import com.illegal.funime.ui.utils.BottomNavItem


@Composable
fun NavigationHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Anime.screen_route) {
        composable(BottomNavItem.Anime.screen_route) {
            AnimeScreen(
                navController = navController
            )
        }
        composable(BottomNavItem.Manga.screen_route) {
            MangaScreen(
                navController = navController
            )
        }
        composable(BottomNavItem.Favourites.screen_route) {
            FavouritesScreen(
                navController = navController
            )
        }
        composable(BottomNavItem.Search.screen_route) {
            SearchScreen(
                navController = navController
            )
        }
        composable(
            "more/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) {
            it.arguments?.getString("category")?.let { it1 ->
                MoreAnimeScreen(
                    category = it1,
                    navController = navController
                )
            }
        }

        composable("animeDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })){
            it.arguments?.getString("id")?.let { id ->
                AnimeDetailScreen(
                    id = id,
                    navController = navController
                )
            }
        }
    }
}