package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
)