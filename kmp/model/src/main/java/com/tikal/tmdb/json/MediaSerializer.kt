package com.tikal.tmdb.json

import com.tikal.tmdb.model.Movie
import com.tikal.tmdb.model.Person
import com.tikal.tmdb.model.Television
import com.tikal.tmdb.model.Media
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object MediaSerializer : JsonContentPolymorphicSerializer<Media>(Media::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<Media> {
        return when (element.jsonObject["media_type"]?.jsonPrimitive?.content) {
            "movie" -> Movie.serializer()
            "person" -> Person.serializer()
            "tv" -> Television.serializer()
            else -> throw Exception("Serializer unknown!")
        }
    }
}