package com.tikal

import androidx.annotation.Keep

/**
 * A generic class that holds a value with its status.
 */
@Keep
sealed class Result<T> {

    @Keep
    class Loading<T> : Result<T>()

    @Keep
    data class Success<T>(val data: T?) : Result<T>()

    @Keep
    data class Error<T>(val exception: Exception, val code: Int = 0) : Result<T>() {
        val message: String? get() = exception.message

        constructor(message: String, code: Int = 0) : this(
            exception = Exception(message),
            code = code
        )

        constructor(other: Error<*>) : this(other.exception, other.code)

        companion object {
            const val HTTP_ERROR_NOT_FOUND = 404
            const val HTTP_ERROR_NOT_SUPPORTED = 422
        }
    }
}