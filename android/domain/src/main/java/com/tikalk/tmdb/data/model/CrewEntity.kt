package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity("crew")
class CrewEntity : CreditEntity() {
    @ColumnInfo("department")
    var department: String? = null

    @ColumnInfo("job")
    var job: String? = null
}