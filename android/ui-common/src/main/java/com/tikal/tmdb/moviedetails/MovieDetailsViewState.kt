package com.tikal.tmdb.moviedetails

import android.net.Uri
import androidx.navigation.NavController
import com.tikal.tmdb.ViewState
import com.tikal.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsViewState : ViewState {
    fun movieDetails(movieId: Long): StateFlow<MovieEntity?>

    fun onPosterClicked(movie: MovieEntity, navController: NavController)
    fun onLinkClicked(movie: MovieEntity, uri: Uri)
}