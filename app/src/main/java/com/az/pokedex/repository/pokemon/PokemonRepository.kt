package com.az.pokedex.repository.pokemon

import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.ResponsePokemonList
import com.az.pokedex.utils.Resource

interface PokemonRepository {
    suspend fun getPokemonCount(): Resource<Int>

    suspend fun getPokemonList(
        limit: Int,
        offset: Int,
    ): Resource<ResponsePokemonList>

    suspend fun getPokemonInfo(
        pokemonName: String,
    ): Resource<ResponsePokemon>

    suspend fun getPokemonInfo(
        pokemonId: Int,
    ): Resource<ResponsePokemon>
}