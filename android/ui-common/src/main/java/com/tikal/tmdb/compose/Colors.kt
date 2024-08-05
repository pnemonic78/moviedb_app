package com.tikal.tmdb.compose

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val appDarkColors = darkColorScheme()

val appLightColors = lightColorScheme()

val ColorScheme.textColorPrimary: Color get() = contentColorFor(primary)

val ColorScheme.textColorSecondary: Color get() = contentColorFor(secondary)
