package com.az.pokedex.repository.pokemon

import androidx.lifecycle.LiveData
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.ResponsePokemonList
import com.az.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<Resource<List<PokemonProfile>>>

    suspend fun getPokemonInfo(
        pokemonId: Int,
    ): Resource<ResponsePokemon>
}