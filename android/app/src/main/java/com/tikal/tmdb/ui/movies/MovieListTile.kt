package com.tikal.tmdb.ui.movies

import android.content.Context
import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.tikal.tmdb.R
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.model.Movie
import java.util.Calendar

private const val parallaxFactor = 0.85f
private const val posterAspectRatio = 1f / 1.5f

@Composable
private fun ThumbnailWidget(movie: Movie) {
    val context = LocalContext.current
    val thumbnailSize = remember { mutableStateOf(IntSize.Zero) }

    val thumbnailHeight = dimensionResource(id = R.dimen.poster_height)
    val thumbnailWidth = thumbnailHeight * posterAspectRatio
    val imageWidth = thumbnailWidth * 1f
    val imageHeight = thumbnailHeight / parallaxFactor
    val thumbnailUrl = getPosterPath(context, movie.posterPath, thumbnailSize.value)
    val thumbnailPainter: Painter = if (thumbnailUrl.isNullOrBlank()) {
        painterResource(id = R.drawable.ic_launcher_foreground)
    } else {
        rememberImagePainter(data = thumbnailUrl)
    }

    Image(
        painter = thumbnailPainter,
        contentDescription = "poster",
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .width(imageWidth)
            .height(imageHeight)
            .onSizeChanged { size -> thumbnailSize.value = size }
    )
}

@Composable
fun TitleWidget(movie: Movie) {
    Text(
        text = movie.title,
        style = MaterialTheme.typography.subtitle1
            .copy(fontWeight = FontWeight.Medium),
        maxLines = 2
    )
}

@Composable
fun VoteAverageWidget(movie: Movie) {
    LinearProgressIndicator(
        modifier = Modifier.fillMaxWidth(),
        progress = movie.voteAverage / 10f,
        color = Color.Yellow,
        backgroundColor = Color.White
    )
}

@Composable
fun ReleaseDateWidget(movie: Movie) {
    val context = LocalContext.current

    Text(
        text = movie.releaseDate?.let {
            DateUtils.formatDateTime(
                context,
                it.timeInMillis,
                DateUtils.FORMAT_SHOW_DATE
            )
        }.orEmpty()
    )
}

@Composable
fun SummaryWidget(movie: Movie) {
    Text(
        text = movie.overview.orEmpty(),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun MovieListTile(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClicked: ((movie: Movie) -> Unit)
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .wrapContentHeight()
            .clickable { onMovieClicked(movie) }
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ThumbnailWidget(movie)
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
            ) {
                TitleWidget(movie)
                Spacer(modifier = Modifier.height(8.dp))
                VoteAverageWidget(movie)
                Spacer(modifier = Modifier.height(8.dp))
                ReleaseDateWidget(movie)
                Spacer(modifier = Modifier.height(12.dp))
                SummaryWidget(movie)
            }
        }
    }
}

private fun getPosterPath(context: Context, path: String?, size: IntSize): String? {
    return TmdbApi.generatePosterUrl(
        context,
        path,
        size.width,
        size.height
    )
}

@Preview
@Composable
private fun MovieListTilePreview() {
    MaterialTheme {
        MovieListTile(movie = movie550) {}
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