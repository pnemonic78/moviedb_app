package com.tikal.tmdb.json.model

import com.tikal.tmdb.json.MediaSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = MediaSerializer::class)
abstract class Media {
    abstract var adult: Boolean

    abstract var id: Long

    abstract var mediaType: MediaType

    abstract var popularity: Double
}