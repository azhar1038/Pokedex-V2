package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseType(
    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type")
    val type: ResponseTypeX
)