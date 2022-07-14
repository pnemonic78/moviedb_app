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

package com.tikal.tmdb.domain

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.Calendar
import java.util.Date

open class Converters {

    @TypeConverter
    fun fromCalendar(value: Calendar?): Long? = value?.timeInMillis

    @TypeConverter
    fun toCalendar(value: Long?): Calendar? =
        value?.let { Calendar.getInstance().apply { timeInMillis = it } }

    @TypeConverter
    fun fromDate(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun toDate(value: Date?): Long? = value?.time

    @TypeConverter
    fun fromTimestamp(value: Long?): Timestamp? = value?.let { Timestamp(it) }

    @TypeConverter
    fun toTimestamp(value: Timestamp?): Long? = value?.time

    @TypeConverter
    fun fromUri(value: Uri?): String? = value?.toString()

    @TypeConverter
    fun toUri(value: String?): Uri? = value?.toUri()

    @TypeConverter
    fun fromListOfLong(value: LongArray?): String? = value?.contentToString()

    @TypeConverter
    fun toListOfLong(value: String?): LongArray? = value?.split(",")?.map { it.toLong() }?.toLongArray()
}