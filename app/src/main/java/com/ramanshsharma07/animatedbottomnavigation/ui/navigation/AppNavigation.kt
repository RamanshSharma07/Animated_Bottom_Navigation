package com.ramanshsharma07.animatedbottomnavigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramanshsharma07.animatedbottomnavigation.ui.screens.HomeScreen
import com.ramanshsharma07.animatedbottomnavigation.ui.screens.ProfileScreen
import com.ramanshsharma07.animatedbottomnavigation.ui.screens.SearchScreen
import com.ramanshsharma07.animatedbottomnavigation.ui.screens.SettingsScreen

@Composable
fun AppNavigation(modifier: Modifier, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = AppDestinations.SCREEN_HOME_ROUTE,
        modifier = modifier
    ) {
        composable(
            route = AppDestinations.SCREEN_HOME_ROUTE
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = AppDestinations.SCREEN_PROFILE
        ){
            ProfileScreen(navController = navController)
        }
        composable(
            route = AppDestinations.SCREEN_SETTINGS
        ){
            SettingsScreen(navController = navController)
        }
        composable(
            route = AppDestinations.SCREEN_SEARCH
        ){
            SearchScreen(navController = navController)
        }
    }
}