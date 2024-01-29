package com.tikalk.moviedb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform