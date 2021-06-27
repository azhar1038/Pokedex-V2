package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseHeartgoldSoulsilver(
    @Json(name = "back_default")
    val backDefault: Any?,
    @Json(name = "back_female")
    val backFemale: Any?,
    @Json(name = "back_shiny")
    val backShiny: Any?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: Any?,
    @Json(name = "front_default")
    val frontDefault: Any?,
    @Json(name = "front_female")
    val frontFemale: Any?,
    @Json(name = "front_shiny")
    val frontShiny: Any?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: Any?
)