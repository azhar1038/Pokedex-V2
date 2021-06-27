package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseVersions(
    @Json(name = "generation-i")
    val generationI: ResponseGenerationI,
    @Json(name = "generation-ii")
    val generationIi: ResponseGenerationIi,
    @Json(name = "generation-iii")
    val generationIii: ResponseGenerationIii,
    @Json(name = "generation-iv")
    val generationIv: ResponseGenerationIv,
    @Json(name = "generation-v")
    val generationV: ResponseGenerationV,
    @Json(name = "generation-vi")
    val generationVi: ResponseGenerationVi,
    @Json(name = "generation-vii")
    val generationVii: ResponseGenerationVii,
    @Json(name = "generation-viii")
    val generationViii: ResponseGenerationViii
)