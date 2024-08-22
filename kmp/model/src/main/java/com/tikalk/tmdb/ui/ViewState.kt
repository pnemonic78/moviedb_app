package com.tikalk.tmdb.ui

import kotlinx.coroutines.flow.StateFlow

interface ViewState {
    val isLoading: StateFlow<Boolean>
}