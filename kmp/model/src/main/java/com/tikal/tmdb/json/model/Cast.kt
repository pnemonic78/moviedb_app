package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Cast : Credit() {
    @SerialName("cast_id")
    var castId: Long = 0

    @SerialName("character")
    var character: String? = null

    @SerialName("order")
    var order: Int = 0
}