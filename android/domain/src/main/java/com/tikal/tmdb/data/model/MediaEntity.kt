package com.tikal.tmdb.data.model

abstract class MediaEntity {
    abstract val adult: Boolean

    abstract val id: Long

    abstract val mediaType: MediaType

    abstract val popularity: Double

    abstract val name: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MediaEntity

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}