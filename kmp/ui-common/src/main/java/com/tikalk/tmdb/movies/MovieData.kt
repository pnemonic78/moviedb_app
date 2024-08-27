package com.tikalk.tmdb.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tikalk.tmdb.data.model.DatesEntity
import com.tikalk.tmdb.data.model.GenreEntity
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.data.model.MoviesPage
import com.tikalk.tmdb.data.model.MoviesPageEntity
import java.util.Calendar
import kotlinx.coroutines.flow.Flow

val movie550 = MovieEntity().apply {
    adult = false
    backdropPath = "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg"
    id = 550
    originalLanguage = "en"
    originalTitle = "Fight Club"
    overview =
        "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."
    popularity = 46.747329
    posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg"
    releaseDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1999)
        set(Calendar.MONTH, Calendar.OCTOBER)
        set(Calendar.DAY_OF_MONTH, 15)
    }
    title = "Fight Club"
    video = false
    voteAverage = 8.3
    voteCount = 11400
}

val movie550Details = MovieEntity().apply {
    adult = false
    backdropPath = "/87hTDiay2N2qWyX4Ds7ybXi9h8I.jpg"
//  belongs_to_collection= null
    budget = 63000000
    homepage = "http=//www.foxmovies.com/movies/fight-club"
    id = 550
    imdbId = "tt0137523"
    originalLanguage = "en"
    originalTitle = "Fight Club"
    overview =
        "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion."
    popularity = 46.747329
    posterPath = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg"
//    productionCompanies = null
//    productionCountries = null
//  productionCompanies= [{ "name= "Twentieth Century Fox Film Corporation", "id= 306 }, {
//      name= "Regency Enterprises", "id= 508
//    }, { "name= "Fox 2000 Pictures", "id= 711 }, {
//      name= "Taurus Film", "id= 20555
//    }, { "name= "Linson Films", "id= 54050 }, {
//      name= "Atman Entertainment", "id= 54051
//    }, { "name= "Knickerbocker Films", "id= 54052 }]
//  productionCountries= [{ "iso_3166_1= "DE", "name= "Germany" }, {
//      iso_3166_1= "US", "name= "United States of America"
//    }]
    releaseDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1999)
        set(Calendar.MONTH, Calendar.OCTOBER)
        set(Calendar.DAY_OF_MONTH, 15)
    }
    revenue = 100853753
    runtime = 139
//    spokenLanguages = listOf(SpokenLanguage(id = "en", name = "English"))
    status = "Released"
    tagline = "Mischief. Mayhem. Soap."
    title = "Fight Club"
    video = false
    voteAverage = 8.3
    voteCount = 11400

    genres = listOf(GenreEntity(id = 18, name = "Drama"))
}

private val moviesList = listOf(
    movie550,
    movie550,
    movie550
)

val page550 = MoviesPage(
    page = MoviesPageEntity(
        dates = DatesEntity(null, null),
        page = 1,
        totalPages = 1,
        totalResults = moviesList.size,
        results =  moviesList
//        type = MoviesPageType.NOW_PLAYING
    ),
    movies = moviesList
)

internal val moviesPreview: Flow<PagingData<MovieEntity>> =
    Pager(PagingConfig(pageSize = 20)) {
        MoviesPreviewSource()
    }.flow
