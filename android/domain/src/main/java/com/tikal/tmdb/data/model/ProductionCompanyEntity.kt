package com.tikal.tmdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_company")
data class ProductionCompanyEntity(
    @ColumnInfo(name="id")
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name="name")
    val name: String
)