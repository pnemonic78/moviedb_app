package com.tikalk.tmdb

import kotlinx.coroutines.flow.StateFlow

interface ViewState {
    val isLoading: StateFlow<Boolean>
}