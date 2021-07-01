package com.az.pokedex.model.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponsePokemon(
    @Json(name = "abilities")
    val abilities: List<ResponseAbility>,
    @Json(name = "base_experience")
    val baseExperience: Int?,
    @Json(name = "forms")
    val forms: Any?,
    @Json(name = "game_indices")
    val gameIndices: Any?,
    @Json(name = "height")
    val height: Int,
    @Json(name = "held_items")
    val heldItems: Any?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "is_default")
    val isDefault: Boolean?,
    @Json(name = "location_area_encounters")
    val locationAreaEncounters: String?,
    @Json(name = "moves")
    val moves: List<ResponseMove>,
    @Json(name = "name")
    val name: String,
    @Json(name = "order")
    val order: Int?,
    @Json(name = "past_types")
    val pastTypes: Any?,
    @Json(name = "species")
    val species: Any?,
    @Json(name = "sprites")
    val sprites: ResponseSprites,
    @Json(name = "stats")
    val stats: List<ResponseStat>,
    @Json(name = "types")
    val types: List<ResponseType>,
    @Json(name = "weight")
    val weight: Int
)