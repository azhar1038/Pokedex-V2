package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationIii(
    @Json(name = "emerald")
    val emerald: ResponseEmerald,
    @Json(name = "firered-leafgreen")
    val fireredLeafgreen: ResponseFireredLeafgreen,
    @Json(name = "ruby-sapphire")
    val rubySapphire: ResponseRubySapphire
)