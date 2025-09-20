package com.ramanshsharma07.animated_nav_bar.bottom_bar

import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Represents a single item in the animated bottom navigation bar.
 *
 * @param route The unique route associated with the navigation destination.
 * @param label The text label to display for the item, especially when selected.
 * @param icon The icon to display for the item.
 */
data class NavBarItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)