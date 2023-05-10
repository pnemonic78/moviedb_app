package com.tikal.tmdb.json

import com.tikal.tmdb.json.model.Media
import com.tikal.tmdb.json.model.Movie
import com.tikal.tmdb.json.model.Person
import com.tikal.tmdb.json.model.Television
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object MediaSerializer : JsonContentPolymorphicSerializer<Media>(Media::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<out Media> {
        return when (element.jsonObject["media_type"]?.jsonPrimitive?.content) {
            "movie" -> Movie.serializer()
            "person" -> Person.serializer()
            "tv" -> Television.serializer()
            else -> throw Exception("Serializer unknown!")
        }
    }
}