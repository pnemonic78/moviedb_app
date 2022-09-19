package com.tikal.tmdb.moviedetails

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import coil.compose.rememberImagePainter
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.common.R

private const val posterAspectRatio = 1f / 1.5f

@Composable
fun MoviePosterContent(
    modifier: Modifier = Modifier, movie: MovieEntity
) {
    val context = LocalContext.current

    val posterPath = getPosterPath(context, movie.posterPath, IntSize(Int.MAX_VALUE, Int.MAX_VALUE))
    val posterPainter: Painter = if (posterPath.isNullOrBlank()) {
        painterResource(id = R.drawable.ic_movie_black)
    } else {
        rememberImagePainter(data = posterPath)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(posterAspectRatio, true),
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

@Preview
@Composable
private fun ThisPreview() {
    MaterialTheme {
        MoviePosterContent(movie = movie550Details)
    }
}