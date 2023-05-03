package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class Credit : Person() {
    @SerialName("credit_id")
    var creditId: String = ""

    // What is this credit for?
    //TODO var media: Media
}
