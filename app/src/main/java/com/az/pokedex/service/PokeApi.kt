package com.az.pokedex.service

import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.ResponsePokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): ResponsePokemonList

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonByName(
        @Path("pokemonName") pokemonName: String
    ): ResponsePokemon

    @GET("pokemon/{pokemonId}")
    suspend fun getPokemonById(
        @Path("pokemonId") pokemonId: Int
    ): ResponsePokemon
}