package com.tikalk.tmdb.ui

import com.tikal.tmdb.data.TmdbRepository
import com.tikal.tmdb.json.model.Movie
import moe.tlaster.precompose.viewmodel.ViewModel

class MoviesViewModel(private val repository: TmdbRepository) : ViewModel() {
    var movie: Movie? = null
}