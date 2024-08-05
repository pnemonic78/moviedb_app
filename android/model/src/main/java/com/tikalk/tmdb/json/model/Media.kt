package com.tikalk.tmdb.json.model

import com.tikalk.tmdb.json.MediaSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MediaSerializer::class)
abstract class Media {
    abstract var adult: Boolean

    abstract var id: Long

    abstract var mediaType: MediaType

    abstract var popularity: Double
}