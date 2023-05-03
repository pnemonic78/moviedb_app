package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonCreditsResponse(
    @SerialName("id")
    val id: Long,

    @SerialName("cast")
    val cast: List<Cast> = emptyList(),

    @SerialName("crew")
    val crew: List<Crew> = emptyList()
)
