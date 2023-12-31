package com.illegal.funime.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.illegal.funime.ui.utils.SpacerHeight
import com.illegal.funime.ui.utils.ThemeSwitcher
import com.illegal.funime.ui.utils.TopBarBack
import com.illegal.funime.ui.viewmodels.SettingsScreenViewModel
import com.illegal.funime.ui.viewmodels.UserPreferenceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    userPreferences : UserPreferenceViewModel
) {
    val theme = userPreferences.theme.collectAsStateWithLifecycle()

    val viewModel: SettingsScreenViewModel = viewModel()
    Scaffold {
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
                    color = MaterialTheme.colorScheme.onSurface
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.deleteFavourites()
                        }
                ) {
                    Text(
                        text = "clear favourites data",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(all = 18.dp)
                    )
                }
                Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "About",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(all = 18.dp)
                    )
                }
            }
        }
    }
}
