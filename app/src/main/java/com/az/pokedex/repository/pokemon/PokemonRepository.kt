package com.az.pokedex.repository.pokemon

import androidx.lifecycle.LiveData
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.ResponsePokemonList
import com.az.pokedex.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
//    val pokemonList: LiveData<Resource<List<PokemonProfile>>>

//    suspend fun getPokemonCount(): Resource<Int>
//
//    suspend fun getPokemonList(
//        limit: Int,
//        offset: Int,
//    ): Resource<ResponsePokemonList>

    fun getPokemonList(): Flow<List<PokemonProfile>>

    suspend fun refreshPokemonList()

    suspend fun getPokemonInfo(
        pokemonName: String,
    ): Resource<ResponsePokemon>

    suspend fun getPokemonInfo(
        pokemonId: Int,
    ): Resource<ResponsePokemon>
}