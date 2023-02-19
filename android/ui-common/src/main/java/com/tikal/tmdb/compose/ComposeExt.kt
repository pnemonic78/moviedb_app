package com.tikal.tmdb.compose

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import coil.compose.rememberImagePainter
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.common.R

typealias OnClickCallback = (() -> Unit)

typealias OnLinkCallback = ((uri: Uri) -> Unit)

@Composable
fun MovieEntity.thumbnailPainter(context: Context, width: Int, height: Int): Painter {
    val url = TmdbApi.generatePosterUrl(context, posterPath, width, height)
    return if (url.isNullOrBlank()) {
        painterResource(id = R.drawable.ic_movie_black)
    } else {
        rememberImagePainter(data = Uri.parse(url), builder = {
            placeholder(R.drawable.ic_movie_black)
        })
    }
}

@Composable
fun MovieEntity.thumbnailPainter(context: Context, size: IntSize): Painter {
    return thumbnailPainter(context, size.width, size.height)
}
