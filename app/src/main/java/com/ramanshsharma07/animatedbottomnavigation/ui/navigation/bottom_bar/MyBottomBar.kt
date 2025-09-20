package com.ramanshsharma07.animatedbottomnavigation.ui.navigation.bottom_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MyBottomBar(navController: NavHostController) {

    val navItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Profile,
        BottomNavItem.Settings
    )

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth().height(85.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // We get the index of the currently selected item
        val selectedIndex = navItems.indexOfFirst { isItemSelected(navController, it.route) }

        // Calculate the horizontal center of each item
        val itemWidth = constraints.maxWidth / navItems.size
        val targetX = (selectedIndex * itemWidth) + (itemWidth / 2)

        // Animate the cradle's X position
        val animatedCradleX by animateFloatAsState(
            targetValue = targetX.toFloat(),
            animationSpec = tween(durationMillis = 300),
            label = "cradleXAnimation"
        )

        // Create an instance of the shape with the animated value
        val bottomBarShape = BottomBarShape(
            selectedItemX = animatedCradleX,
            cradleWidth = 80.dp,
            cradleHeight = 50.dp
        )

        // This Surface is just the background shape. It has no content.
        Surface(
            modifier = Modifier
                .fillMaxWidth()
//                .height(65.dp)
                .height(80.dp)
                .shadow(elevation = 8.dp, shape = bottomBarShape),
            shape = bottomBarShape,
            color = Color(0xFF2C2C2C)
        ) {}

        // This Row contains the icons and is drawn ON TOP of the Surface.
        // This is what prevents the icons from being clipped.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                val isSelected = isItemSelected(navController, item.route)
                AnimatedNavItem(
                    item = item,
                    isSelected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}