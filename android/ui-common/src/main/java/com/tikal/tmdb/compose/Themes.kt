package com.tikal.tmdb.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: ComposableContent
) {
    MaterialTheme(
        colors = if (isDarkTheme) appDarkColors else appLightColors,
        shapes = appShapes,
        content = content
    )
}