package com.tikalk.tmdb.moviedetails

import android.net.Uri
import android.text.format.DateUtils
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.tikalk.tmdb.api.TmdbApi
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.OnClickCallback
import com.tikalk.tmdb.compose.OnLinkCallback
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.movies.movie550Details
import com.tikalk.tmdb.movies.posterPainter
import io.kamel.image.KamelImage
import java.text.NumberFormat
import java.util.Locale
import movie_db_kmp.shared.generated.resources.Res
import movie_db_kmp.shared.generated.resources.budget_label
import movie_db_kmp.shared.generated.resources.genres_label
import movie_db_kmp.shared.generated.resources.popularity_label
import movie_db_kmp.shared.generated.resources.release_date_label
import movie_db_kmp.shared.generated.resources.revenue_label
import movie_db_kmp.shared.generated.resources.runtime_label
import movie_db_kmp.shared.generated.resources.summary_label
import org.jetbrains.compose.resources.stringResource

private const val posterAspectRatio = 1f / 1.5f

@Composable
fun MovieDetailsContent(
    modifier: Modifier = Modifier,
    movie: MovieEntity,
    onPosterClick: OnClickCallback?,
    onLinkClick: OnLinkCallback?
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val posterSize = remember { mutableStateOf(IntSize.Zero) }
    val posterPainter = movie.posterPainter(size = posterSize.value)

    val textTheme = MaterialTheme.typography

    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US).apply {
        maximumFractionDigits = 0
    }

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = movie.title,
            style = textTheme.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(0.5f)) {
                KamelImage(
                    resource = posterPainter,
                    contentDescription = movie.title,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(posterAspectRatio, true)
                        .onSizeChanged { size -> posterSize.value = size }
                        .clickable(
                            enabled = (onPosterClick != null),
                            onClick = { onPosterClick?.invoke() })
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    var iconCount = 0

                    movie.homepage?.let {
                        iconCount++

                        val url = it
                        val uri = Uri.parse(url)
                        IconButton(onClick = { onLinkClick?.invoke(uri) }) {
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = url
                            )
                        }
                    }
                    movie.imdbId?.let {
                        if (iconCount > 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        iconCount++

                        val url = TmdbApi.generateImdbMovieUrl(it)
                        val uri = Uri.parse(url)
                        IconButton(onClick = { onLinkClick?.invoke(uri) }) {
                            Icon(
                                imageVector = Icons.Outlined.Storage,
                                contentDescription = url
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = stringResource(Res.string.popularity_label),
                    style = textTheme.titleLarge
                )
                RatingBar(
                    value = (movie.voteAverage / 2).toFloat(),
                    config = RatingBarConfig()
                        .isIndicator(true)
                        .numStars(5)
                        .size(24.dp)
                        .stepSize(StepSize.HALF)
                        .style(RatingBarStyle.HighLighted),
                    onValueChange = {},
                    onRatingChanged = {}
                )
                movie.releaseDate?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.release_date_label),
                        style = textTheme.titleLarge
                    )
                    Text(
                        text = DateUtils.formatDateTime(
                            context,
                            it.timeInMillis,
                            DateUtils.FORMAT_SHOW_DATE
                        )
                    )
                }
                movie.runtime?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.runtime_label),
                        style = textTheme.titleLarge
                    )
                    Text(
                        text = DateUtils.formatElapsedTime(
                            it * 60L
                        )
                    )
                }
                if (movie.budget > 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.budget_label),
                        style = textTheme.titleLarge
                    )
                    Text(
                        text = currencyFormatter.format(movie.budget)
                    )
                }
                movie.revenue?.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.revenue_label),
                        style = textTheme.titleLarge
                    )
                    Text(
                        text = currencyFormatter.format(it)
                    )
                }
                movie.genres.let {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(Res.string.genres_label),
                        style = textTheme.titleLarge
                    )
                    Text(
                        text = it.joinToString(separator = ", ") { it.name }
                    )
                }
            }
        }
        movie.overview?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(Res.string.summary_label),
                style = textTheme.titleLarge
            )
            Text(
                text = it
            )
        }
        // TODO carousel videos
        // TODO carousel cast members
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val onPosterClick = { println("Clicked poster") }
    val onLinkClick: OnLinkCallback = { println("Clicked link $it") }
    AppTheme {
        MovieDetailsContent(
            movie = movie550Details,
            onPosterClick = onPosterClick,
            onLinkClick = onLinkClick
        )
    }
}
