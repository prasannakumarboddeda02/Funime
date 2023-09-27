package com.illegal.funime.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.illegal.funime.ui.utils.SpacerHeight
import com.illegal.funime.ui.utils.ThemeSwitcher
import com.illegal.funime.ui.utils.TopBarBack
import com.illegal.funime.ui.viewmodels.UserPreferenceViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    userPreferences : UserPreferenceViewModel
) {
    val theme = userPreferences.theme.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 18.dp)
    ) {
        TopBarBack(
            text = "settings",
            onClick = {
                navController.popBackStack()
            })
        SpacerHeight(height = 10.dp)
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 18.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Theme",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    ThemeSwitcher(
                        darkTheme = when (theme.value) {
                            1 -> false
                            2 -> true
                            else -> isSystemInDarkTheme()
                        },
                        size = 50.dp,
                        onClick = {
                            userPreferences.storeTheme(if (theme.value == 1) 2 else 1)
                        }
                    )
                }
            }
            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.tertiary
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 18.dp)
            ) {
                Text(
                    text = "clear favourites data",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 18.dp)
            ) {
                Text(
                    text = "About",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
