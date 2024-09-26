package com.tikalk.tmdb.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.tikalk.tmdb.compose.items
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.movies.OnMovieClickCallback
import com.tikalk.tmdb.ui.LoadingGridTile
import com.tikalk.tmdb.ui.MovieGridTile
import com.tikalk.tmdb.ui.dimen
import kotlinx.coroutines.flow.Flow

@Composable
fun MoviesCarousel(
    modifier: Modifier = Modifier,
    movies: Flow<PagingData<MovieEntity>>,
    onMovieClicked: OnMovieClickCallback
) {
    val pagingItems = movies.collectAsLazyPagingItems()

    MoviesCarousel(
        modifier = modifier,
        pagingItems = pagingItems,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun MoviesCarousel(
    modifier: Modifier = Modifier,
    pagingItems: LazyPagingItems<MovieEntity>,
    onMovieClicked: OnMovieClickCallback
) {
    val scrollState = rememberLazyListState()
    val tileWidth = dimen.posterWidth

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
