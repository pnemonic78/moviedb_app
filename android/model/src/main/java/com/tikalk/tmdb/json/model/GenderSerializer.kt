package com.tikalk.tmdb.json.model

import com.tikalk.tmdb.json.EnumIntSerializer

class GenderSerializer : EnumIntSerializer<Gender>(Gender.entries)