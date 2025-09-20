package com.ramanshsharma07.animatedbottomnavigation.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColors = darkColorScheme(
    background = Color(0xFF121714),
    onBackground = Color.White,
    primary = Color.White,
    secondary = Color(0xFF94E0B3)
)

private val LightColors = lightColorScheme(
    background = Color.White,
    onBackground = Color(0xFF0E0101),
    primary = Color(0xFF2B362E),
    secondary = Color(0xFF94E0B3)
)

@Composable
fun MyAppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
