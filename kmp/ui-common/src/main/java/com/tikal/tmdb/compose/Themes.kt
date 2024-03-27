package com.tikal.tmdb.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: ComposableContent
) {
    MaterialTheme(
        colorScheme = if (isDarkTheme) appDarkColors else appLightColors,
        shapes = appShapes,
        content = content
    )
}

@Composable
fun TmdbApp(content: ComposableContent) {
    PreComposeApp {
        AppTheme(content = content)
    }
}