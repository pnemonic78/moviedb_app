package com.tikal.tmdb

import androidx.lifecycle.ViewModel
import com.tikal.tmdb.data.source.TmdbDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel(protected val repository: TmdbDataSource) :
    ViewModel(),
    UiState {

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean> = _isLoading

    protected suspend fun showLoadingIndicator(active: Boolean) {
        _isLoading.emit(active)
    }

    private val _title = MutableStateFlow("")
    override val title: MutableStateFlow<String> = _title
}