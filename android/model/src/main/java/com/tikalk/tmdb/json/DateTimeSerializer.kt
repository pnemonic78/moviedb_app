package com.tikalk.tmdb.json

import androidx.annotation.Keep
import java.text.SimpleDateFormat
import java.util.*

@Keep
class DateTimeSerializer : NullableCalendarSerializer(SimpleDateFormat("yyyy-MM-dd", Locale.US))