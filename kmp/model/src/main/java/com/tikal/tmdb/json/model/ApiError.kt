package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    @SerialName("status_code")
    val statusCode: Int,
    @SerialName("status_message")
    val statusMessage: String? = null,
    @SerialName("success")
    val success: Boolean = false
)
