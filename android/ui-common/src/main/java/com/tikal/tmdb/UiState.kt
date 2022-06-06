package com.tikal.tmdb

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface UiState {
    val isLoading: StateFlow<Boolean>
    val title: MutableStateFlow<String>
}