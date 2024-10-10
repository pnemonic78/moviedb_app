package com.tikalk.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = GenderSerializer::class)
enum class Gender {
    @SerialName("0")
    unknown,
    @SerialName("1")
    female,
    @SerialName("2")
    male
}