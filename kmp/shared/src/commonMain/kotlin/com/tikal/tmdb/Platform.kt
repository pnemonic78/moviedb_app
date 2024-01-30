package com.tikal.tmdb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform