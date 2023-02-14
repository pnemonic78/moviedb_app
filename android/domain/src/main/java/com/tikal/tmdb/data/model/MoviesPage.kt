package com.tikal.tmdb.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MoviesPage(
    @Embedded
    val page: MoviesPageEntity,
    @Relation(
        parentColumn = "id",// MoviesPageEntity
        entity = MovieEntity::class,
        entityColumn = "id",// MovieEntity
        associateBy = Junction(
            MoviesPageCrossRef::class,
            parentColumn = "page_id",
            entityColumn = "movie_id"
        )
    )
    val movies: List<MovieEntity>
)