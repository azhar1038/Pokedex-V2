package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationV(
    @Json(name = "black-white")
    val blackWhite: ResponseBlackWhite
)