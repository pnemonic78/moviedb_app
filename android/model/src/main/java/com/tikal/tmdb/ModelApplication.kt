package com.tikal.tmdb

import android.app.Application
import timber.log.Timber

open class ModelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}