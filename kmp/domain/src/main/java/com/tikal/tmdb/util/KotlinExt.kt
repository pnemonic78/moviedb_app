package com.tikal.tmdb.util

import java.util.Calendar

fun Long.toCalendar(): Calendar = Calendar.getInstance().apply { timeInMillis = this@toCalendar }

fun String.toLongArray(): LongArray {
    val value: String = this
    val lastIndex = value.lastIndex
    if (lastIndex > 1) {
        val firstChar = value[0]
        val lastChar = value[lastIndex]
        if ((firstChar == '[') && (lastChar == ']') && (lastIndex > 1)) {
            return value.substring(1, lastIndex)
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { it.toLong() }
                .toLongArray()
        }
    }
    return LongArray(0)
}