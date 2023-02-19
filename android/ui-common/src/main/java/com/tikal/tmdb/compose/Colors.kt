package com.tikal.tmdb.compose

import android.annotation.SuppressLint
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.contentColorFor
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

val appLightColors = lightColors()

val appDarkColors = darkColors()

val Colors.textColorPrimary: Color get() = contentColorFor(primary)

val Colors.textColorSecondary: Color get() = contentColorFor(secondary)
