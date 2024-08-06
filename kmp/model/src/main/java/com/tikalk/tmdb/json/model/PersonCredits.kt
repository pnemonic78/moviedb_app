package com.tikalk.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonCredits(
    @SerialName("id")
    val id: Long,

    @SerialName("cast")
    val cast: List<Cast> = emptyList(),

    @SerialName("crew")
    val crew: List<Crew> = emptyList()
)
