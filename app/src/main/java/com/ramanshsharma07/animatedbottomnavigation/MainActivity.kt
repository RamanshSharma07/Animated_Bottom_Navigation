package com.ramanshsharma07.animatedbottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramanshsharma07.animated_nav_bar.bottom_bar.AnimatedBottomNavBar
import com.ramanshsharma07.animated_nav_bar.bottom_bar.NavBarItem
import com.ramanshsharma07.animatedbottomnavigation.ui.navigation.AppDestinations
import com.ramanshsharma07.animatedbottomnavigation.ui.navigation.AppNavigation
import com.ramanshsharma07.animatedbottomnavigation.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                val navController = rememberNavController()

//                val navItems = listOf(
//                    NavBarItem("home", "Home", Icons.Default.Home),
//                    NavBarItem("search", "Search", Icons.Default.Search),
//                    NavBarItem("profile", "Profile", Icons.Default.Person),
//                    NavBarItem("settings", "Settings", Icons.Default.Settings)
//                )
                val navItems = listOf(
                    NavBarItem(AppDestinations.SCREEN_HOME_ROUTE, "Home", Icons.Default.Home),
                    NavBarItem(AppDestinations.SCREEN_SEARCH, "Search", Icons.Default.Search),
                    NavBarItem(AppDestinations.SCREEN_PROFILE, "Profile", Icons.Default.Person),
                    NavBarItem(AppDestinations.SCREEN_SETTINGS, "Settings", Icons.Default.Settings)
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedBottomNavBar(
                            navItems = navItems,
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}
