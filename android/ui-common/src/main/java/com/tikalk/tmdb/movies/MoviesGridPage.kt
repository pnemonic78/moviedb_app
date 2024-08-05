package com.tikalk.tmdb.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tikalk.tmdb.LoadingGridTile
import com.tikalk.tmdb.MovieGridTile
import com.tikalk.tmdb.compose.AppTheme
import com.tikalk.tmdb.compose.items
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.moviesPreview
import com.tikalk.tmdb.ui.common.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesGridPage(
    modifier: Modifier = Modifier,
    viewState: MoviesPageViewState,
    navController: NavController
) {
    val pagingItems = viewState.movies.collectAsLazyPagingItems()

    MoviesGridPage(
        modifier = modifier,
        pagingItems = pagingItems,
        onMovieClicked = { movie -> viewState.onMovieClicked(movie, navController) }
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
        columns = GridCells.Adaptive(dimensionResource(id = R.dimen.posterWidth)),
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

@Preview(name = "normal", widthDp = 450)
@Preview(name = "wide", widthDp = 650)
@Composable
private fun ThisPreview() {
    val viewState = object : MoviesPageViewState {
        override val isLoading: StateFlow<Boolean> = MutableStateFlow(false)
        override val isGridPage: StateFlow<Boolean> = MutableStateFlow(true)
        override val movies: Flow<PagingData<MovieEntity>> = moviesPreview

        override fun onMovieClicked(movie: MovieEntity, navController: NavController) = Unit
        override fun onToggleLayout() = Unit
    }
    val navController = rememberNavController()
    AppTheme {
        MoviesGridPage(viewState = viewState, navController = navController)
    }
}