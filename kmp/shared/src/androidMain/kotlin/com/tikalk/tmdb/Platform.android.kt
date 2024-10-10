package com.tikalk.tmdb

import android.os.Build
import androidx.room.Room
import com.tikalk.tmdb.domain.TmdbDb

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    override val databaseBuilder = Room.databaseBuilder(
        androidContext!!,
        TmdbDb::class.java,
        "tmdb"
    )
}

actual fun getPlatform(): Platform = AndroidPlatform()