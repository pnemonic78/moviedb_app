package com.tikal.tmdb.json.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Credit : Person() {
    @SerialName("credit_id")
    var creditId: String = ""

    // What is this credit for?
    @Polymorphic
    var media: Media? = null
}
