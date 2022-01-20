package com.tikal.tmdb.ui.movies

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.tikal.tmdb.R
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.model.Movie
import java.util.Calendar

@Composable
fun MovieListTile(movie: Movie, modifier: Modifier = Modifier) {
    val posterSize = remember { mutableStateOf(IntSize.Zero) }

    Card(
        modifier = modifier
            .padding(8.dp)
            .wrapContentHeight()
            .alpha(0.5f)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            val posterPath = getPosterPath(movie.posterPath, posterSize.value)
            val posterPainter: Painter = if (posterPath.isNullOrBlank()) {
                painterResource(id = R.drawable.ic_launcher_foreground)
            } else {
                rememberImagePainter(data = posterPath)
            }

            Image(
                painter = posterPainter,//rememberImagePainter(posterPath),
                contentDescription = "poster",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.poster_height))
                    .aspectRatio(1f / 1.5f, true)
                    .onSizeChanged { size -> posterSize.value = size }
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.subtitle1
                        .copy(fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

private fun getPosterPath(path: String?, size: IntSize): String? {
    return TmdbApi.generatePosterUrl(
        Resources.getSystem(),
        path,
        size.width,
        size.height
    )
}

@Preview
@Composable
private fun MovieListTilePreview() {
    MaterialTheme {
        MovieListTile(movie = movie550)
    }
}

internal val movie550 = Movie(
    adult = false,
    backdropPath = "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
    genreIds = emptyList(),
    id = 550,
    originalLanguage = "en",
    originalTitle = "Fight Club",
    overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
    popularity = 46.747329f,
    posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
    releaseDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1999)
        set(Calendar.MONTH, Calendar.OCTOBER)
        set(Calendar.DAY_OF_MONTH, 15)
    },
    title = "Fight Club",
    video = false,
    voteAverage = 8.3f,
    voteCount = 11400
)