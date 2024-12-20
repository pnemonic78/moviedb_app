package com.tikalk.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("id")
    val id: Long? = 0L,

    @SerialName("cast")
    val cast: List<Cast> = emptyList(),

    @SerialName("crew")
    val crew: List<Crew> = emptyList()
)
