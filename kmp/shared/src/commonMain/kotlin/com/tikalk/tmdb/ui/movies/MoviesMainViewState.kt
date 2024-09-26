package com.tikalk.tmdb.ui.movies

import androidx.paging.PagingData
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesMainViewState(
    override val isLoading: Boolean = false,
    val moviesNowPlaying: Flow<PagingData<MovieEntity>> = emptyFlow(),
    val moviesPopular: Flow<PagingData<MovieEntity>> = emptyFlow(),
    val moviesTopRated: Flow<PagingData<MovieEntity>> = emptyFlow(),
    val moviesUpcoming: Flow<PagingData<MovieEntity>> = emptyFlow()
) : ViewState