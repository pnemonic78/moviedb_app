package com.tikalk.tmdb

import platform.UIKit.UIDevice

private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val databaseBuilder = RoomDatabase.Builder<TmdbDb> {
        val dbFilePath = documentDirectory() + "/tmdb.db"
        return Room.databaseBuilder<TmdbDb>(
            name = dbFilePath
        )
    }
}

actual fun getPlatform(): Platform = IOSPlatform()