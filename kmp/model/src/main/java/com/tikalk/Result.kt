package com.tikalk

/**
 * A generic class that holds a value with its status.
 */
sealed class Result<T> {

    class Loading<T> : Result<T>()

    data class Success<T>(val data: T?) : Result<T>()

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