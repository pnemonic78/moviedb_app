package com.tikalk.tmdb.ui.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.tikal.tmdb.json.model.Movie
import com.tikalk.tmdb.api.TmdbApi
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieCell(
    movie: Movie,
    onClick: (movieId: Long, title: String) -> Unit
) {
    val density = LocalDensity.current

    val width = 100.dp
    val height = 150.dp
    val widthPx = with(density) { width.toPx() }.toInt()
    val heightPx = with(density) { height.toPx() }.toInt()
    val posterPath = TmdbApi.generatePosterUrl(movie, widthPx, heightPx)

    Column(
        modifier = Modifier.width(width)
    ) {
        if (posterPath.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .size(width, height)
                    .background(color = Color.LightGray)
            ) {}
        } else {
            KamelImage(
                modifier = Modifier
                    .size(width, height)
                    .clickable {
                        onClick(movie.id, movie.title)
                    },
                resource = asyncPainterResource(posterPath),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }
        Text(text = movie.title)
    }
}
