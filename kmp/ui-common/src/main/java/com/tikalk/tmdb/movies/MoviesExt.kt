package com.tikalk.tmdb.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.IntSize
import com.tikalk.tmdb.api.TmdbApi
import com.tikalk.tmdb.data.model.MovieEntity
import io.kamel.core.ExperimentalKamelApi
import io.kamel.core.Resource
import io.kamel.image.asyncPainterResource
import movie_db_kmp.ui_common.generated.resources.Res
import movie_db_kmp.ui_common.generated.resources.ic_movie_black
import org.jetbrains.compose.resources.painterResource

typealias OnMovieClickCallback = ((movie: MovieEntity) -> Unit)

@OptIn(ExperimentalKamelApi::class)
@Composable
fun MovieEntity.posterPainter(width: Int, height: Int): Resource<Painter> {
    val url = TmdbApi.generatePosterUrl(posterPath, width, height)
    return if (url.isNullOrBlank()) {
        asyncPainterResource(Res.drawable.ic_movie_black)
    } else {
        asyncPainterResource(
            data = url,
            onLoadingPainter = { Result.success(painterResource(Res.drawable.ic_movie_black)) }
        )
    }
}

@Composable
fun MovieEntity.posterPainter(size: IntSize) = posterPainter(size.width, size.height)
