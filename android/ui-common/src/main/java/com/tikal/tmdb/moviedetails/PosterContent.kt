package com.tikal.tmdb.moviedetails

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.ui.common.R

private const val posterAspectRatio = 1f / 1.5f

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PosterContent(
    modifier: Modifier = Modifier,
    posterPathSuffix: String?
) {
    val context = LocalContext.current

    val posterSize = remember { mutableStateOf(IntSize.Zero) }
    val posterPath = getPosterPath(context, posterPathSuffix, IntSize(Int.MAX_VALUE, Int.MAX_VALUE))
    val posterPainter: Painter = if (posterPath.isNullOrBlank()) {
        painterResource(id = R.drawable.ic_movie_black)
    } else {
        rememberImagePainter(data = posterPath)
    }
    val scale = remember { mutableStateOf(1f) }
    val scaleValue = maxOf(1f, minOf(6f, scale.value))

    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }
    val scaleValue1 = scaleValue - 1f
    val offsetXMax = (posterSize.value.width * scaleValue1) / 2f
    val offsetYMax = (posterSize.value.height * scaleValue1) / 2f
    val offsetXMin = -offsetXMax
    val offsetYMin = -offsetYMax
    val offsetXValue = maxOf(offsetXMin, minOf(offsetXMax, offsetX.value))
    val offsetYValue = maxOf(offsetYMin, minOf(offsetYMax, offsetY.value))

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale.value *= zoom
                    offsetX.value += pan.x
                    offsetY.value += pan.y
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(posterAspectRatio, true)
                .onSizeChanged { size -> posterSize.value = size }
                .graphicsLayer(
                    scaleX = scaleValue,
                    scaleY = scaleValue,
                    translationX = offsetXValue,
                    translationY = offsetYValue
                ),
            painter = posterPainter,
            contentDescription = "poster",
            contentScale = ContentScale.FillHeight
        )
    }
}

private fun getPosterPath(context: Context, path: String?, size: IntSize): String? {
    return TmdbApi.generatePosterUrl(
        context, path, size.width, size.height
    )
}
