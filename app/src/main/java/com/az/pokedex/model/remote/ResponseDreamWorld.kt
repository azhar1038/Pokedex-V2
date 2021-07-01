package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDreamWorld(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?
)