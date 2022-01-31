package com.tikal.tmdb.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

fun <T> concat(vararg elements: Flow<T>): Flow<T> = flow {
    for (element in elements) {
        emitAll(element)
    }
}
