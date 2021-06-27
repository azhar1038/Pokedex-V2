package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseGenerationIv(
    @Json(name = "diamond-pearl")
    val diamondPearl: ResponseDiamondPearl,
    @Json(name = "heartgold-soulsilver")
    val heartgoldSoulsilver: ResponseHeartgoldSoulsilver,
    @Json(name = "platinum")
    val platinum: ResponsePlatinum
)