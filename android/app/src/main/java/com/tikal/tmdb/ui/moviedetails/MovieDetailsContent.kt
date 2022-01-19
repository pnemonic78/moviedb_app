package com.tikal.tmdb.ui.moviedetails

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.tikal.tmdb.R
import com.tikal.tmdb.api.TmdbApi
import com.tikal.tmdb.model.Genre
import com.tikal.tmdb.model.MovieDetails
import com.tikal.tmdb.model.SpokenLanguage
import java.util.Calendar

@Composable
fun MovieDetailsContent(movie: MovieDetails, modifier: Modifier = Modifier) {
    val posterSize = remember { mutableStateOf(IntSize.Zero) }

    Column(
        modifier = modifier.padding(8.dp)
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
                    .fillMaxWidth()
                    .weight(0.4f)
                    .aspectRatio(1f / 1.5f, true)
                    .onSizeChanged { size -> posterSize.value = size }
            )
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 8.dp)
            ) {
                Text(movie.title, style = MaterialTheme.typography.h5)
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
private fun MovieDetailsContentPreview() {
    MaterialTheme {
        MovieDetailsContent(movie = movie550)
    }
}

internal val movie550 = MovieDetails(
    adult = false,
    backdropPath = "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg",
//  belongs_to_collection= null,
    budget = 63000000,
    genres = listOf(Genre(id = 18, name = "Drama")),
    homepage = "http=//www.foxmovies.com/movies/fight-club",
    id = 550,
    imdbId = "tt0137523",
    originalLanguage = "en",
    originalTitle = "Fight Club",
    overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
    popularity = 46.747329f,
    posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg",
    productionCompanies = null,
    productionCountries = null,
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
    spokenLanguages = listOf(SpokenLanguage(id = "en", name = "English")),
    status = "Released",
    tagline = "Mischief. Mayhem. Soap.",
    title = "Fight Club",
    video = false,
    voteAverage = 8.3f,
    voteCount = 11400
)