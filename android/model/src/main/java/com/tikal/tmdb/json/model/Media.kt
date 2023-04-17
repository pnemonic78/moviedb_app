package com.tikal.tmdb.json.model

abstract class Media {
    abstract val adult: Boolean

    abstract val id: Long

    abstract val mediaType: MediaType

    abstract val popularity: Float
}