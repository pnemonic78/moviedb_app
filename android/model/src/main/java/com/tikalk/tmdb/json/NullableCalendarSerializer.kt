package com.tikalk.tmdb.json

import androidx.annotation.Keep
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

// ISO 8601, e.g. "2015-09-26T18:23:50.250UTC"
@Keep
open class NullableCalendarSerializer(private val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US)) : KSerializer<Calendar?> {
    init {
        formatter.timeZone = TimeZone.getTimeZone("UTC")
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): Calendar? {
        if (decoder.decodeNotNullMark()) {
            val value = decoder.decodeString()
            return Calendar.getInstance().apply {
                time = formatter.parse(value)!!
                timeZone = formatter.timeZone
            }
        }
        return decoder.decodeNull()
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: Calendar?) {
        if (value == null) {
            encoder.encodeNull()
            return
        }
        formatter.timeZone = value.timeZone
        encoder.encodeString(formatter.format(value.time))
    }
}