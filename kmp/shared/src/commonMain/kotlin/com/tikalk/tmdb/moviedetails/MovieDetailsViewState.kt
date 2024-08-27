package com.tikalk.tmdb.moviedetails

import android.net.Uri
import androidx.navigation.NavController
import com.tikalk.tmdb.ViewState
import com.tikalk.tmdb.data.model.MovieEntity
import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsViewState : ViewState {
    fun movieDetails(movieId: Long): StateFlow<MovieEntity?>

    fun onPosterClicked(movie: MovieEntity, navController: NavController)
    fun onLinkClicked(movie: MovieEntity, uri: Uri)
}