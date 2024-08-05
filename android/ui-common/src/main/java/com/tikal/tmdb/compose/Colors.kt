package com.tikal.tmdb.compose

import androidx.compose.material.Colors
import androidx.compose.material.contentColorFor
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val appLightColors = lightColors()

val appDarkColors = darkColors()

val Colors.textColorPrimary: Color get() = contentColorFor(primary)

val Colors.textColorSecondary: Color get() = contentColorFor(secondary)
