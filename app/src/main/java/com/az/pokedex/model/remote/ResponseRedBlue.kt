package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseRedBlue(
    @Json(name = "back_default")
    val backDefault: Any?,
    @Json(name = "back_gray")
    val backGray: Any?,
    @Json(name = "front_default")
    val frontDefault: Any?,
    @Json(name = "front_gray")
    val frontGray: Any?
)