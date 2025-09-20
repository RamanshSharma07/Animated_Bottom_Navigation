package com.ramanshsharma07.animatedbottomnavigation.ui.navigation.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramanshsharma07.animatedbottomnavigation.ui.navigation.AppDestinations

// Sealed class to represent our navigation items
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem(AppDestinations.SCREEN_HOME_ROUTE, Icons.Default.Home, "Home")
    object Search : BottomNavItem(AppDestinations.SCREEN_SEARCH, Icons.Default.Search, "Search")
    object Profile : BottomNavItem(AppDestinations.SCREEN_PROFILE, Icons.Default.Person, "Profile")
    object Settings : BottomNavItem(AppDestinations.SCREEN_SETTINGS, Icons.Default.Settings, "Settings")
}