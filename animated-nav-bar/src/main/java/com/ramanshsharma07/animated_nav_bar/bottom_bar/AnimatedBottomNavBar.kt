package com.ramanshsharma07.animated_nav_bar.bottom_bar

import android.util.LayoutDirection
import android.util.Size
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import kotlin.io.path.Path
import kotlin.io.path.moveTo
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.*
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AnimatedBottomNavBar(
    modifier: Modifier = Modifier,
    navItems: List<NavBarItem>,
    navController: NavController,
    barColor: androidx.compose.ui.graphics.Color = Color(0xFF550000),
    circleColor: androidx.compose.ui.graphics.Color = Color(0xFF550000),
    selectedIconColor: androidx.compose.ui.graphics.Color = Color(0xFFeed9c4),
    unselectedIconColor: androidx.compose.ui.graphics.Color = Color(0xFFeed9c4),
    selectedTextColor: androidx.compose.ui.graphics.Color = Color(0xFFeed9c4),
    animationDurationMillis: Int = 300
) {
    BoxWithConstraints (
        modifier = Modifier.fillMaxWidth().height(85.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        val boxWithConstraintsScope = this
        val selectedIndex = navItems.indexOfFirst { isItemSelected(navController, it.route) }.coerceAtLeast(0)
        val itemWidth = constraints.maxWidth / navItems.size
        val targetX = (selectedIndex * itemWidth) + (itemWidth / 2)

        val animatedCradleX by animateFloatAsState(
            targetValue = targetX.toFloat(),
            animationSpec = tween(durationMillis = animationDurationMillis),
            label = "cradleXAnimation"
        )

        val bottomBarShape = BottomBarShape(selectedItemX = animatedCradleX)

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .shadow(elevation = 8.dp, shape = bottomBarShape),
            shape = bottomBarShape,
            color = barColor
        ) {}

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                AnimatedNavItem(
                    item = item,
                    isSelected = isItemSelected(navController, item.route),
                    circleColor = circleColor,
                    selectedIconColor = selectedIconColor,
                    unselectedIconColor = unselectedIconColor,
                    selectedTextColor = selectedTextColor,
                    animationDurationMillis = animationDurationMillis,
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


@Composable
private fun AnimatedNavItem(
    item: NavBarItem,
    isSelected: Boolean,
    circleColor: Color,
    selectedIconColor: Color,
    unselectedIconColor: Color,
    selectedTextColor: Color,
    animationDurationMillis: Int,
    onClick: () -> Unit
) {
    val offsetY by animateDpAsState(
        targetValue = if (isSelected) (-44).dp else 0.dp,
        animationSpec = tween(durationMillis = animationDurationMillis), label = "offsetYAnimation"
    )
    val offsetYInPixels = with(LocalDensity.current) { offsetY.toPx() }
    val iconColor by animateColorAsState(
        targetValue = if (isSelected) selectedIconColor else unselectedIconColor,
        animationSpec = tween(durationMillis = animationDurationMillis), label = "iconColorAnimation"
    )
    val animatedCircleColor by animateColorAsState(
        targetValue = if (isSelected) circleColor else Color.Transparent,
        animationSpec = tween(durationMillis = animationDurationMillis), label = "circleColorAnimation"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) selectedTextColor else Color.Transparent,
        animationSpec = tween(durationMillis = animationDurationMillis), label = "textColorAnimation"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .graphicsLayer { this.translationY = offsetYInPixels }
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(animatedCircleColor)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = item.label,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

private class BottomBarShape(private val selectedItemX: Float) : Shape {
    private val cradleWidth: Dp = 80.dp
    private val cradleHeight: Dp = 48.dp
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {
        val cradleWidthPx = with(density) { cradleWidth.toPx() }
        val cradleHeightPx = with(density) { cradleHeight.toPx() }
        val cradleStartX = selectedItemX - (cradleWidthPx / 2)
        return Outline.Generic(Path().apply {
            moveTo(0f, 0f)
            lineTo(cradleStartX, 0f)
            quadraticBezierTo(x1 = selectedItemX, y1 = cradleHeightPx, x2 = cradleStartX + cradleWidthPx, y2 = 0f)
            lineTo(size.width.toFloat(), 0f)
            lineTo(size.width.toFloat(), size.height.toFloat())
            lineTo(0f, size.height.toFloat())
            close()
        })
    }
}

@Composable
private fun isItemSelected(navController: NavController, route: String): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route == route
}