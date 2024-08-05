/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2022, Tikal Knowledge, Ltd.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * • Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * • Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * • Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.tikalk.tmdb.domain

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter
import com.tikalk.tmdb.util.toCalendar
import com.tikalk.tmdb.util.toLongArray
import java.sql.Timestamp
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

typealias DateTime = Calendar

class Converters {

    fun fromCalendar(value: Calendar): Long = value.timeInMillis

    fun toCalendar(value: Long): Calendar = value.toCalendar()

    @TypeConverter
    fun fromNullableCalendar(value: Calendar?): Long? = value?.let { fromCalendar(it) }

    @TypeConverter
    fun toNullableCalendar(value: Long?): Calendar? = value?.let { toCalendar(it) }

    fun fromDate(value: Long): Date = Date(value)

    fun toDate(value: Date): Long = value.time

    @TypeConverter
    fun fromNullableDate(value: Long?): Date? = value?.let { fromDate(it) }

    @TypeConverter
    fun toNullableDate(value: Date?): Long? = value?.let { toDate(it) }

    fun fromTimestamp(value: Long): Timestamp = Timestamp(value)

    fun toTimestamp(value: Timestamp): Long = value.time

    @TypeConverter
    fun fromNullableTimestamp(value: Long?): Timestamp? = value?.let { fromTimestamp(it) }

    @TypeConverter
    fun toNullableTimestamp(value: Timestamp?): Long? = value?.let { toTimestamp(it) }

    fun fromUri(value: Uri): String = value.toString()

    fun toUri(value: String): Uri = value.toUri()

    @TypeConverter
    fun fromNullableUri(value: Uri?): String? = value?.let { fromUri(it) }

    @TypeConverter
    fun toNullableUri(value: String?): Uri? = value?.let { toUri(it) }

    fun fromLongArray(value: LongArray): String = value.contentToString()

    fun toLongArray(value: String): LongArray = value.toLongArray()

    @TypeConverter
    fun fromNullableLongArray(value: LongArray?): String? = value?.let { fromLongArray(it) }

    @TypeConverter
    fun toNullableLongArray(value: String?): LongArray? = value?.let { toLongArray(it) }

    @TypeConverter
    fun fromListOfString(value: List<String>): String = Json.encodeToString(value)

    @TypeConverter
    fun toListOfString(value: String?): List<String> =
        if (value.isNullOrEmpty()) emptyList() else Json.decodeFromString(value)

    fun fromLocale(value: Locale): String = value.toLanguageTag()

    fun toLocale(value: String): Locale = Locale.forLanguageTag(value)

    @TypeConverter
    fun fromNullableLocale(value: Locale?): String? = value?.let { fromLocale(it) }

    @TypeConverter
    fun toNullableLocale(value: String?): Locale? = value?.let { toLocale(it) }
}