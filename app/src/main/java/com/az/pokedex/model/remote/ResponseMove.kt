package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseMove(
    @Json(name = "move")
    val move: ResponseMoveX,
    @Json(name = "version_group_details")
    val versionGroupDetails: List<ResponseVersionGroupDetail>
)