package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationIi(
    @Json(name = "crystal")
    val crystal: ResponseCrystal,
    @Json(name = "gold")
    val gold: ResponseGold,
    @Json(name = "silver")
    val silver: ResponseSilver
)