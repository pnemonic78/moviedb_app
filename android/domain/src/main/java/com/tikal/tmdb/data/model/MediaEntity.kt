package com.tikal.tmdb.data.model

abstract class MediaEntity {
    abstract val adult: Boolean

    abstract val id: Long

    abstract val mediaType: MediaType

    abstract val popularity: Double
}