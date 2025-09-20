package com.ramanshsharma07.animatedbottomnavigation.ui.navigation.bottom_bar

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection


class BottomBarShape(
    private val selectedItemX: Float,
    private val cradleWidth: Dp,
    private val cradleHeight: Dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
            val cradleWidthPx = with(density) { cradleWidth.toPx() }
            val cradleHeightPx = with(density) { cradleHeight.toPx() }

            // The starting X-coordinate of the cradle is now based on the selected item's position
            val cradleStartX = selectedItemX - (cradleWidthPx / 2)

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(cradleStartX, 0f)
            quadraticBezierTo(
                x1 = selectedItemX,
                y1 = cradleHeightPx,
                x2 = cradleStartX + cradleWidthPx,
                y2 = 0f
            )
            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}