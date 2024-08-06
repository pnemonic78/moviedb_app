package com.tikalk.tmdb.ui

import com.tikalk.Result

data class UiState<T>(
    val state: Result<T> = Result.Loading()
)
