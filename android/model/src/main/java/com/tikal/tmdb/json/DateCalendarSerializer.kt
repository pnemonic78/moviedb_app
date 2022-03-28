package com.tikal.tmdb.json

import androidx.annotation.Keep
import java.text.SimpleDateFormat
import java.util.*

@Keep
class DateCalendarSerializer : NullableCalendarSerializer(SimpleDateFormat("yyyy-MM-dd", Locale.US))