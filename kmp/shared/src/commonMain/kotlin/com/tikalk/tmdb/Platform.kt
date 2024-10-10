package com.tikalk.tmdb

import androidx.room.RoomDatabase
import com.tikalk.tmdb.domain.TmdbDb

interface Platform {
    val name: String
    val databaseBuilder: RoomDatabase.Builder<TmdbDb>
}

expect fun getPlatform(): Platform