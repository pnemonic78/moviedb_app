package com.tikal.tmdb.json.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Crew(
    @SerialName("department")
    var department: String? = null,
    @SerialName("job")
    var job: String? = null
) : Credit()