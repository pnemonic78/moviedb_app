package com.tikalk.tmdb.json.model

import com.tikalk.tmdb.json.MediaSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MediaSerializer::class)
abstract class Media {
    open var adult: Boolean = false

    open var id: Long = 0

    open var mediaType: MediaType = MediaType.all

    open var popularity: Double = 0.0
}