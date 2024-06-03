package com.tikalk.tmdb.ui

import com.tikal.tmdb.data.TmdbRepository
import com.tikal.tmdb.model.*
import moe.tlaster.precompose.viewmodel.ViewModel

class MoviesViewModel(private val repository: TmdbRepository) : ViewModel() {
    var movie: Movie? = null
}