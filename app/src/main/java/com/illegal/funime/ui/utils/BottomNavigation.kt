package com.illegal.funime.ui.utils

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavController
){
    val itemsList = listOf(
        BottomNavItem.Anime,
        BottomNavItem.Manga,
        BottomNavItem.Favourites,
        BottomNavItem.Search
    )
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute: String? = navBackStackEntry?.destination?.route
    NavigationBar(

    ) {
        itemsList.forEach {navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.screen_route,
                onClick = {
                    navController.navigate(route = navItem.screen_route) {
                        popUpTo(id = navController.graph.startDestinationId) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(
                        text = navItem.title
                    )
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = navItem.icon),
                        contentDescription = navItem.title
                    )
                }
            )
        }
    }
}