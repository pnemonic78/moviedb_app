package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    @SerialName("id") var id: Long,
    @SerialName("name") var name: String
)