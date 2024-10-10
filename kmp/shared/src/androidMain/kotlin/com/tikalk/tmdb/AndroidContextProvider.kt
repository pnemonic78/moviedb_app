package com.tikalk.tmdb

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.pm.ProviderInfo
import android.database.Cursor
import android.net.Uri

val androidContext get() = AndroidContextProvider.ANDROID_CONTEXT

class AndroidContextProvider : ContentProvider() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var ANDROID_CONTEXT: Context? = null
    }

    private var info: ProviderInfo? = null

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(): Boolean {
        ANDROID_CONTEXT = context

        // Workaround for "Android context is not initialized".
        val clazz = Class.forName("org.jetbrains.compose.resources.AndroidContextProvider")
        val providerClass = clazz as Class<ContentProvider>
        val provider = providerClass.getDeclaredConstructor().newInstance()
        provider.attachInfo(context, info)
        provider.onCreate()
        this.info = null

        return true
    }

    override fun attachInfo(context: Context, info: ProviderInfo?) {
        if (info == null) {
            throw NullPointerException("AndroidContextProvider ProviderInfo cannot be null.")
        }
        // So if the authorities equal the library internal ones, the developer forgot to set his applicationId
        if ("com.tikalk.tmdb.shared.resources.AndroidContextProvider" == info.authority) {
            throw IllegalStateException(
                "Incorrect provider authority in manifest. Most likely due to a "
                    + "missing applicationId variable your application\'s build.gradle."
            )
        }

        this.info = info
        super.attachInfo(context, info)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}