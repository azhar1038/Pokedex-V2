package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationI(
    @Json(name = "red-blue")
    val redBlue: ResponseRedBlue,
    @Json(name = "yellow")
    val yellow: ResponseYellow
)