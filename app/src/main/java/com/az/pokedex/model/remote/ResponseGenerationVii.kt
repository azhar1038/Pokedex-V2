package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationVii(
    @Json(name = "icons")
    val icons: ResponseIcons,
    @Json(name = "ultra-sun-ultra-moon")
    val ultraSunUltraMoon: ResponseUltraSunUltraMoon
)