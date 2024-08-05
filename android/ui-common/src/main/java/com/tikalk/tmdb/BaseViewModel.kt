package com.tikalk.tmdb

import androidx.lifecycle.ViewModel
import com.tikalk.tmdb.data.source.TmdbDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel(protected val repository: TmdbDataSource) :
    ViewModel(),
    ViewState {

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> = _isLoading

    protected suspend fun showLoadingIndicator(active: Boolean) {
        _isLoading.emit(active)
    }
}