package com.tikalk.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tikalk.tmdb.compose.items
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.LoadingGridTile
import com.tikalk.tmdb.ui.MovieGridTile
import com.tikalk.tmdb.ui.dimen

@Composable
fun MoviesGridPage(
    modifier: Modifier = Modifier,
    viewState: MoviesPageViewState,
    onMovieClicked: OnMovieClickCallback
) {
    val pagingItems = viewState.movies.collectAsLazyPagingItems()

    MoviesGridPage(
        modifier = modifier,
        pagingItems = pagingItems,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun MoviesGridPage(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<MovieEntity>,
    onMovieClicked: OnMovieClickCallback
) {
    val scrollState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier,
        state = scrollState,
        columns = GridCells.Adaptive(dimen.posterWidth),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = pagingItems, key = { it.id }) { movie ->
            movie?.let {
                MovieGridTile(
                    movie = it,
                    onMovieClicked = onMovieClicked
                )
            } ?: LoadingGridTile()
        }
    }
}
