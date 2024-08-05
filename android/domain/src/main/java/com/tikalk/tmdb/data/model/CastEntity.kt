package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cast")
class CastEntity : CreditEntity() {
    @ColumnInfo("cast_id")
    var castId: Long = 0

    @ColumnInfo("character")
    var character: String? = null

    @ColumnInfo("order")
    var order: Int = 0
}