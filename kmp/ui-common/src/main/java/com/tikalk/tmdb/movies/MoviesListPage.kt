package com.tikalk.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tikalk.tmdb.compose.items
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.LoadingListTile
import com.tikalk.tmdb.ui.MovieListTile

@Composable
fun MoviesListPage(
    modifier: Modifier = Modifier,
    viewState: MoviesPageViewState,
    onMovieClicked: OnMovieClickCallback
) {
    val pagingItems = viewState.movies.collectAsLazyPagingItems()

    MoviesListPage(
        modifier = modifier,
        pagingItems = pagingItems,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun MoviesListPage(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<MovieEntity>,
    onMovieClicked: OnMovieClickCallback
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = pagingItems, key = { it.id }) { movie ->
            movie?.let {
                MovieListTile(
                    movie = it,
                    onMovieClicked = onMovieClicked
                )
            } ?: LoadingListTile()
        }
    }
}
