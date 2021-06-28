package com.az.pokedex.repository.pokemon

import android.util.Log
import androidx.lifecycle.LiveData
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.ResponsePokemonList
import com.az.pokedex.model.remote.asPokemonProfile
import com.az.pokedex.service.PokeApi
import com.az.pokedex.service.database.PokemonDao
import com.az.pokedex.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    private val pokeApi: PokeApi,
    private val pokemonDao: PokemonDao,
): PokemonRepository {

//    override suspend fun getPokemonCount(): Resource<Int> {
//        val localCount: Int = pokemonDao.count()?:0
//        val remoteCount = try{
//            val response = pokeApi.getPokemonList(1, 0)
//            response.count
//        }catch(e: Exception){
////            return Resource.Error("Something went wrong!")
//            localCount
//        }
//        return Resource.Success(remoteCount)
//    }
//
//    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<ResponsePokemonList> {
//        val response = try{
//            pokeApi.getPokemonList(limit, offset)
//        }catch(e: Exception){
//            return Resource.Error("Something went wrong!")
//        }
//        return Resource.Success(response)
//    }

    override fun getPokemonList(): LiveData<List<PokemonProfile>> {
        return pokemonDao.getAll()
    }

    override suspend fun refreshPokemonList() {
        withContext(Dispatchers.IO){
            val localCount = pokemonDao.count()?: 0
            val remoteCount = try{
                val response = pokeApi.getPokemonList(1,0)
                response.count
            }catch(e: Exception){
                localCount
            }
            val limit = remoteCount - localCount
            if(limit > 0){
                val response = pokeApi.getPokemonList(limit, localCount)
                val pokemons: List<PokemonProfile> = response.results.map {
                    it.asPokemonProfile()
                }
                pokemonDao.insertAll(pokemons)
            }
        }
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