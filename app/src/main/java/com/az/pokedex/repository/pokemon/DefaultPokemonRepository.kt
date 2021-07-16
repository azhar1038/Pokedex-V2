package com.az.pokedex.repository.pokemon

import android.util.Log
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.model.remote.asPokemonProfile
import com.az.pokedex.service.PokeApi
import com.az.pokedex.service.database.PokemonDao
import com.az.pokedex.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultPokemonRepository @Inject constructor(
    private val pokeApi: PokeApi,
    private val pokemonDao: PokemonDao,
) : PokemonRepository {

    override fun getPokemonList(): Flow<Resource<List<PokemonProfile>>> {
        return flow {
            emit(Resource.Loading())
            val source = pokemonDao.getAll().map { Resource.Success(it) }
            withContext(Dispatchers.IO){
                launch {
                    val localCount = pokemonDao.count() ?: 0
                    val remoteCount = try {
                        val response = pokeApi.getPokemonList(1, 0)
                        response.count
                    } catch (e: Exception) {
                        localCount
                    }
                    val limit = remoteCount - localCount
                    if (limit > 0) {
                        try {
                            val response = pokeApi.getPokemonList(limit, localCount)
                            val pokemons: List<PokemonProfile> = response.results.map {
                                it.asPokemonProfile()
                            }
                            pokemonDao.insertAll(pokemons)
                        } catch (e: Exception) {
                            Log.i("TAG", "getPokemonList: Failed to fetch data")
                        }
                    }
                }
            }
            emitAll(source)
        }
    }

    override suspend fun getPokemonInfo(pokemonId: Int): Resource<ResponsePokemon> {
        val response = try {
            pokeApi.getPokemonById(pokemonId)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }

//    override suspend fun refreshPokemonList() {
//        withContext(Dispatchers.IO) {
//            val localCount = pokemonDao.count() ?: 0
//            val remoteCount = try {
//                val response = pokeApi.getPokemonList(1, 0)
//                response.count
//            } catch (e: Exception) {
//                localCount
//            }
//            val limit = remoteCount - localCount
//            if (limit > 0) {
//                val response = pokeApi.getPokemonList(limit, localCount)
//                val pokemons: List<PokemonProfile> = response.results.map {
//                    it.asPokemonProfile()
//                }
//                pokemonDao.insertAll(pokemons)
//            }
//        }
//    }
//

}