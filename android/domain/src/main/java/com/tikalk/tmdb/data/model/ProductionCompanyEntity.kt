package com.tikalk.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "production_company")
data class ProductionCompanyEntity(
    @ColumnInfo(name="id")
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name="name")
    val name: String,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
)