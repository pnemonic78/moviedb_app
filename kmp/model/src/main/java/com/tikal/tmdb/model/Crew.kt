package com.tikal.tmdb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Crew : Credit() {
    @SerialName("department")
    var department: String? = null

    @SerialName("job")
    var job: String? = null
}