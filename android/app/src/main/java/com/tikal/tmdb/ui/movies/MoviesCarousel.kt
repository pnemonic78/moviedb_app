package com.tikal.tmdb.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.tikal.tmdb.LoadingGridTile
import com.tikal.tmdb.MovieGridTile
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.movies.OnMovieClickCallback
import com.tikal.tmdb.ui.common.R

@Composable
fun MoviesCarousel(
    modifier: Modifier = Modifier,
    viewState: MoviesCarouselViewState,
    navController: NavHostController
) {
    val pagingItems = viewState.movies.collectAsLazyPagingItems()

    MoviesCarousel(
        modifier = modifier,
        pagingItems = pagingItems,
        onMovieClicked = { viewState.onMovieClicked(it, navController) }
    )
}

@Composable
fun MoviesCarousel(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<MovieEntity>,
    onMovieClicked: OnMovieClickCallback
) {
    val scrollState = rememberLazyListState()
    val tileWidth = dimensionResource(id = R.dimen.posterWidth)

    LazyRow(
        modifier = modifier,
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = pagingItems, key = { it.id }) { movie ->
            movie?.let {
                MovieGridTile(
                    modifier = Modifier.width(tileWidth),
                    movie = it,
                    onMovieClicked = onMovieClicked
                )
            } ?: LoadingGridTile(
                modifier = Modifier.width(tileWidth)
            )
        }
    }
}
