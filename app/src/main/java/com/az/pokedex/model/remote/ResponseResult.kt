package com.az.pokedex.model.remote


import com.az.pokedex.model.PokemonProfile
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseResult(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)

fun ResponseResult.asPokemonProfile(): PokemonProfile {
    val name = this.name
    val url = this.url
    val id = if(url.endsWith("/")){
        url.dropLast(1).takeLastWhile { it.isDigit() }
    }else{
        url.takeLastWhile { it.isDigit() }
    }
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    return PokemonProfile(
        id = id.toLong(),
        name = name,
        imageUrl = imageUrl,
        favorite = false
    )
}