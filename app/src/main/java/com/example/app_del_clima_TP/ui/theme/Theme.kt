package com.example.app_del_clima_TP.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    secondary = secondaryLight,
    error = errorLight,
    background = backgroundLight
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    secondary = secondaryDark,
    error = errorDark,
    background = backgroundDark
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    getWindowSize()

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}


