package com.tikal.tmdb.json

import java.text.SimpleDateFormat
import java.util.*

class DateTimeSerializer : NullableCalendarSerializer(SimpleDateFormat("yyyy-MM-dd", Locale.US))