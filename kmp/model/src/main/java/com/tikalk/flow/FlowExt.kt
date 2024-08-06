package com.tikalk.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

suspend fun <T> concat(vararg elements: Flow<T>): Flow<T> = flow {
    for (element in elements) {
        emitAll(element)
    }
}
