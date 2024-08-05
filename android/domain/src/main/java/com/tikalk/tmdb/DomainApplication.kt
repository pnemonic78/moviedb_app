package com.tikalk.tmdb

import android.annotation.SuppressLint
import android.content.Context

open class DomainApplication : ModelApplication() {

    init {
        myContext = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var myContext: Context
    }
}