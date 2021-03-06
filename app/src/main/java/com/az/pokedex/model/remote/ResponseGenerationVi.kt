package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationVi(
    @Json(name = "omegaruby-alphasapphire")
    val omegarubyAlphasapphire: ResponseOmegarubyAlphasapphire,
    @Json(name = "x-y")
    val xY: ResponseXY
)