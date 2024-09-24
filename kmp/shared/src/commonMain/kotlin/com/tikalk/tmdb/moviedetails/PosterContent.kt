package com.tikalk.tmdb.moviedetails

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.tikalk.tmdb.api.TmdbApi.generatePosterUrl
import io.kamel.core.ExperimentalKamelApi
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import movie_db_kmp.shared.generated.resources.Res
import movie_db_kmp.shared.generated.resources.ic_movie_black
import org.jetbrains.compose.resources.painterResource

private const val posterAspectRatio = 1f / 1.5f

@OptIn(ExperimentalKamelApi::class)
@Composable
fun PosterContent(
    modifier: Modifier = Modifier,
    posterPathSuffix: String?
) {
    val posterSize = remember { mutableStateOf(IntSize.Zero) }
    val posterPath = generatePosterUrl(posterPathSuffix, Int.MAX_VALUE, Int.MAX_VALUE)
    val posterPainter: Resource<Painter> = if (posterPath.isNullOrBlank()) {
        asyncPainterResource(Res.drawable.ic_movie_black)
    } else {
        asyncPainterResource(
            data = posterPath,
            onLoadingPainter = { Result.success(painterResource(Res.drawable.ic_movie_black)) }
        )
    }
    val scale = remember { mutableFloatStateOf(1f) }
    val scaleValue = maxOf(1f, minOf(6f, scale.floatValue))

    val offsetX = remember { mutableFloatStateOf(0f) }
    val offsetY = remember { mutableFloatStateOf(0f) }
    val scaleValue1 = scaleValue - 1f
    val offsetXMax = (posterSize.value.width * scaleValue1) / 2f
    val offsetYMax = (posterSize.value.height * scaleValue1) / 2f
    val offsetXMin = -offsetXMax
    val offsetYMin = -offsetYMax
    val offsetXValue = maxOf(offsetXMin, minOf(offsetXMax, offsetX.floatValue))
    val offsetYValue = maxOf(offsetYMin, minOf(offsetYMax, offsetY.floatValue))

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale.floatValue *= zoom
                    offsetX.floatValue += pan.x
                    offsetY.floatValue += pan.y
                }
            },
        contentAlignment = Alignment.Center
    ) {
        KamelImage(
            modifier = Modifier
                .aspectRatio(posterAspectRatio, true)
                .onSizeChanged { size -> posterSize.value = size }
                .graphicsLayer(
                    scaleX = scaleValue,
                    scaleY = scaleValue,
                    translationX = offsetXValue,
                    translationY = offsetYValue
                ),
            resource = posterPainter,
            contentDescription = "poster",
            contentScale = ContentScale.FillHeight
        )
    }
}
