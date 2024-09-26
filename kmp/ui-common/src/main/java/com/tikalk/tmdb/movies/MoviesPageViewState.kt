package com.tikalk.tmdb.movies

import androidx.paging.PagingData
import com.tikalk.tmdb.data.model.MovieEntity
import com.tikalk.tmdb.ui.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviesPageViewState(
    override val isLoading: Boolean = false,
    val isGridPage: Boolean = true,
    val movies: Flow<PagingData<MovieEntity>> = emptyFlow()
) : ViewState