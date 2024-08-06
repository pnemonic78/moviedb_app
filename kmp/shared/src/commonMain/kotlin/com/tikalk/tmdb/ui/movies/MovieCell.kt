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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tikal.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.api.TmdbApi
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.movie550
import com.tikalk.tmdb.movies.OnMovieClickCallback
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieCell(
    movie: MovieEntity,
    onClick: OnMovieClickCallback
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
                        onClick(movie)
                    },
                resource = asyncPainterResource(posterPath),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }
        Text(modifier = Modifier.width(width), text = movie.title)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCC00CC, widthDp = 200)
@Composable
private fun ThisPreview() {
    AppTheme {
        MovieCell(movie = movie550) { movie ->
            println("click movie $movie")
        }
    }
}
