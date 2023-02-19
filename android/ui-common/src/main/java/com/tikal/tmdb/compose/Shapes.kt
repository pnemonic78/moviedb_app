package com.tikal.tmdb.compose

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val CornerSizeZeroDp: Dp = 0.dp
val CornerSizeZero = CornerSize(CornerSizeZeroDp)
val CornerSizeLargeDp: Dp = 20.dp
val CornerSizeLarge = CornerSize(CornerSizeLargeDp)
val CornerSizeMediumDp: Dp = 12.dp
val CornerSizeMedium = CornerSize(CornerSizeMediumDp)
val CornerSizeSmallDp: Dp = 4.dp
val CornerSizeSmall = CornerSize(CornerSizeSmallDp)

private val materialShapes = Shapes()

val appShapes = materialShapes.copy(
    large = materialShapes.large.copy(CornerSizeLarge),
    medium = materialShapes.medium.copy(CornerSizeMedium),
    small = materialShapes.small.copy(CornerSizeSmall)
)