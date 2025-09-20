package com.ramanshsharma07.animatedbottomnavigation.ui.navigation.bottom_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedNavItem(item: BottomNavItem, isSelected: Boolean, onClick: () -> Unit) {
    // Animate the vertical offset in Dp
    val offsetY by animateDpAsState(
        targetValue = if (isSelected) (-40).dp else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = "offsetYAnimation"
    )

    // Convert the Dp value to Px for the graphicsLayer
    val offsetY_in_pixels = with(LocalDensity.current) { offsetY.toPx() }

    // Animate colors
    val iconColor by animateColorAsState(
//        targetValue = if (isSelected) Color.White else Color.Gray,
//        targetValue = if (isSelected) Color(red = 238f, green = 217f, blue = 196f) else Color.Gray,
        targetValue = Color(0xFFeed9c4),
        animationSpec = tween(durationMillis = 300),
        label = "iconColorAnimation"
    )
    val circleColor by animateColorAsState(
//        targetValue = if (isSelected) Color(0xFF009688) else Color.Transparent,
//        targetValue = if (isSelected) Color(red = 35f, green = 0f, blue = 0f) else Color.Transparent,
        targetValue = if (isSelected) Color(0xFF550000) else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "circleColorAnimation"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFeed9c4) else Color.Transparent,
//        targetValue = if (isSelected) Color(0xFF9C27B0) else Color.Transparent,
        animationSpec = tween(durationMillis = 300),
        label = "textColorAnimation"
    )

    // This Column is the root of our item. Its layout is what we see.
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            // KEY CHANGE 1: Use graphicsLayer to translate the drawing vertically.
            // This does NOT affect layout and therefore does NOT get clipped.
            .graphicsLayer {
                this.translationY = offsetY_in_pixels
            }
//            .padding(bottom = 8.dp)
    ) {
        // KEY CHANGE 2: The Box for the icon is now inside the Column.
        // It's responsible for the circular background.
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(circleColor)
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

        // The text is now a separate element within the Column,
        // ensuring it appears BELOW the icon Box.
        Text(
            text = item.label,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier.padding(
                top = 10.dp,
//                bottom = 2.dp
            )
        )
    }
}