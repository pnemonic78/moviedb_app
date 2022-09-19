package com.tikal.tmdb.moviedetails

import android.content.Context
import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.ui.common.R
import java.util.Calendar

private const val posterAspectRatio = 1f / 1.5f

@Composable
fun MovieDetailsContent(
    movie: MovieEntity,
    modifier: Modifier = Modifier,
    onPosterClick: (() -> Unit)?
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val posterSize = remember { mutableStateOf(IntSize.Zero) }
    val posterPath = getPosterPath(context, movie.posterPath, posterSize.value)
    val posterPainter: Painter = if (posterPath.isNullOrBlank()) {
        painterResource(id = R.drawable.ic_movie_black)
    } else {
        rememberImagePainter(data = posterPath)
    }

    val textTheme = MaterialTheme.typography

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(state = scrollState)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = posterPainter,
                contentDescription = "poster",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .aspectRatio(posterAspectRatio, true)
                    .onSizeChanged { size -> posterSize.value = size }
                    .clickable(
                        enabled = (onPosterClick != null),
                        onClick = { onPosterClick?.invoke() })
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = movie.title,
                    style = textTheme.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.popularity_label),
                    style = textTheme.h6
                )
                RatingBar(
                    value = movie.voteAverage / 2,
                    config = RatingBarConfig()
                        .isIndicator(true)
                        .numStars(5)
                        .size(24.dp)
                        .stepSize(StepSize.HALF)
                        .style(RatingBarStyle.HighLighted),
                    onValueChange = {},
                    onRatingChanged = {}
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.release_date_label),
                    style = textTheme.h6
                )
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
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.summary_label),
            style = textTheme.h6
        )
        Text(
            text = movie.overview.orEmpty()
        )
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
private fun ThisPreview() {
    val onPosterClick = { println("Clicked poster") }
    MaterialTheme {
        MovieDetailsContent(movie = movie550Details, onPosterClick = onPosterClick)
    }
}

val movie550Details = MovieEntity(
    adult = false,
    backdropPath = "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
//  belongs_to_collection= null,
    budget = 63000000,
//    genres = listOf(Genre(id = 18, name = "Drama")),
    homepage = "http=//www.foxmovies.com/movies/fight-club",
    id = 550,
    imdbId = "tt0137523",
    originalLanguage = "en",
    originalTitle = "Fight Club",
    overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
    popularity = 46.747329f,
    posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
//    productionCompanies = null,
//    productionCountries = null,
//  productionCompanies= [{ "name= "Twentieth Century Fox Film Corporation", "id= 306 }, {
//      name= "Regency Enterprises", "id= 508
//    }, { "name= "Fox 2000 Pictures", "id= 711 }, {
//      name= "Taurus Film", "id= 20555
//    }, { "name= "Linson Films", "id= 54050 }, {
//      name= "Atman Entertainment", "id= 54051
//    }, { "name= "Knickerbocker Films", "id= 54052 }],
//  productionCountries= [{ "iso_3166_1= "DE", "name= "Germany" }, {
//      iso_3166_1= "US", "name= "United States of America"
//    }],
    releaseDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1999)
        set(Calendar.MONTH, Calendar.OCTOBER)
        set(Calendar.DAY_OF_MONTH, 15)
    },
    revenue = 100853753,
    runtime = 139,
//    spokenLanguages = listOf(SpokenLanguage(id = "en", name = "English")),
    status = "Released",
    tagline = "Mischief. Mayhem. Soap.",
    title = "Fight Club",
    video = false,
    voteAverage = 8.3f,
    voteCount = 11400
)