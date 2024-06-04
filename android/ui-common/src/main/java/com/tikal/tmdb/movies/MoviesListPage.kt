package com.tikal.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tikal.tmdb.LoadingListTile
import com.tikal.tmdb.MovieListTile
import com.tikal.tmdb.compose.AppTheme
import com.tikal.tmdb.compose.items
import com.tikal.tmdb.data.model.MovieEntity
import com.tikal.tmdb.moviesPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesListPage(
    modifier: Modifier = Modifier,
    viewState: MoviesPageViewState,
    navController: NavController
) {
    val pagingItems = viewState.movies.collectAsLazyPagingItems()

    MoviesListPage(
        modifier = modifier,
        pagingItems = pagingItems,
        onMovieClicked = { movie -> viewState.onMovieClicked(movie, navController) }
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
        items(items = pagingItems /*FIXME, key = { it.id }*/) { movie ->
            movie?.let {
                MovieListTile(
                    movie = it,
                    onMovieClicked = onMovieClicked
                )
            } ?: LoadingListTile()
        }
    }
}

@Preview
@Composable
private fun ThisPreview() {
    val viewState = object : MoviesPageViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(false)
        override val movies: Flow<PagingData<MovieEntity>> = moviesPreview

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onToggleLayout() = Unit
    }
    val navController = rememberNavController()
    AppTheme {
        MoviesListPage(viewState = viewState, navController = navController)
    }
}