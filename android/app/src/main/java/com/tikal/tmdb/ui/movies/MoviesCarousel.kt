package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tikal.tmdb.MovieGridTile
import com.tikal.tmdb.data.model.DatesEntity
import com.tikal.tmdb.data.model.MoviesPage
import com.tikal.tmdb.data.model.MoviesPageEntity
import com.tikal.tmdb.data.model.MoviesPageType
import com.tikal.tmdb.movie550
import com.tikal.tmdb.movies.OnMovieClickCallback
import com.tikal.tmdb.ui.common.R

@Composable
fun MoviesCarousel(
    modifier: Modifier = Modifier,
    viewState: MoviesCarouselViewState,
    navController: NavHostController
) {
    val movies = viewState.movies.collectAsState(initial = emptyList())

    MoviesCarousel(
        modifier = modifier,
        pages = movies.value,
        onMovieClicked = { viewState.onMovieClicked(it, navController) }
    )
}

@Composable
fun MoviesCarousel(
    modifier: Modifier = Modifier,
    pages: List<MoviesPage>,
    onMovieClicked: OnMovieClickCallback
) {
    val scrollState = rememberLazyListState()
    val movies = pages.flatMap { it.movies }
    val tileWidth = dimensionResource(id = R.dimen.posterWidth)

    LazyRow(
        modifier = modifier,
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.size) { index ->
            val movie = movies[index]
            MovieGridTile(
                modifier = Modifier.width(tileWidth),
                movie = movie,
                onMovieClicked = onMovieClicked
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFCC00CC, widthDp = 400)
@Composable
private fun ThisPreview() {
    val moviesList = listOf(movie550, movie550)
    val page = MoviesPage(
        page = MoviesPageEntity(
            dates = DatesEntity(),
            page = 1,
            totalPages = 1,
            totalResult = moviesList.size,
            type = MoviesPageType.NOW_PLAYING
        ),
        movies = moviesList
    )
    val onMovieClicked: OnMovieClickCallback = { println("movie clicked $it") }

    MaterialTheme {
        MoviesCarousel(
            pages = listOf(page),
            onMovieClicked = onMovieClicked
        )
    }
}