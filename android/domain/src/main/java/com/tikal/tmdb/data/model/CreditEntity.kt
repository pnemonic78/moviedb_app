package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity("credit")
open class CreditEntity : PersonEntity() {
    @ColumnInfo("credit_id")
    var creditId: String = ""

    @ColumnInfo("media_id")
    // TODO foreign key to media table
    var mediaId: Long = 0

    // What is this credit for?
    @Ignore
    var media: MediaEntity? = null
}
