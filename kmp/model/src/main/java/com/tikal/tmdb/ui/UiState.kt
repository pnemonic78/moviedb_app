package com.tikal.tmdb.ui

import com.tikal.Result

data class UiState<T>(
    val state: Result<T> = Result.Loading()
)
