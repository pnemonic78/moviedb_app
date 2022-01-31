package com.tikal.tmdb.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flowOf

fun <T> concat(vararg elements: Flow<T>): Flow<T> = flowOf(*elements).flattenConcat()
