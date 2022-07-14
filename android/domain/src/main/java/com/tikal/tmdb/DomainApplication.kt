package com.tikal.tmdb

import androidx.room.Room
import androidx.room.RoomDatabase
import com.tikal.tmdb.domain.TmdbDb

open class DomainApplication : ModelApplication() {

    lateinit var db: RoomDatabase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            TmdbDb::class.java, "tmdb"
        ).build()
    }
}