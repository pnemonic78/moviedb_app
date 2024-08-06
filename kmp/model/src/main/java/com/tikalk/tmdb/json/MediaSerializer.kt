package com.tikalk.tmdb.json

import com.tikalk.tmdb.json.model.Media
import com.tikalk.tmdb.json.model.Movie
import com.tikalk.tmdb.json.model.Person
import com.tikalk.tmdb.json.model.Television
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