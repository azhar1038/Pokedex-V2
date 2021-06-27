package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseOther(
    @Json(name = "dream_world")
    val dreamWorld: ResponseDreamWorld,
    @Json(name = "official-artwork")
    val officialArtwork: ResponseOfficialArtwork
)