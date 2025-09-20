package com.ramanshsharma07.animatedbottomnavigation.ui.navigation.bottom_bar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun isItemSelected(navController: NavController, route: String): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route == route
}