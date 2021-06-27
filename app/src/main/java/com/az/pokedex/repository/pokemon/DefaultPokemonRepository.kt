package com.az.pokedex.repository.pokemon

import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.ResponsePokemonList
import com.az.pokedex.service.PokeApi
import com.az.pokedex.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    private val pokeApi: PokeApi
): PokemonRepository {
    override suspend fun getPokemonCount(): Resource<Int> {
        val response = try{
            pokeApi.getPokemonList(1, 0)
        }catch(e: Exception){
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response.count)
    }

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<ResponsePokemonList> {
        val response = try{
            pokeApi.getPokemonList(limit, offset)
        }catch(e: Exception){
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<ResponsePokemon> {
        val response = try{
            pokeApi.getPokemonByName(pokemonName)
        }catch(e: Exception){
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonId: Int): Resource<ResponsePokemon> {
        val response = try{
            pokeApi.getPokemonById(pokemonId)
        }catch(e: Exception){
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }
}