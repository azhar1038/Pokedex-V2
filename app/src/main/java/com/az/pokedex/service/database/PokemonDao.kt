package com.az.pokedex.service.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.az.pokedex.model.PokemonProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT COUNT(id) FROM pokemon_list")
    fun count(): Int?

    @Insert
    fun insertAll(pokemons: List<PokemonProfile>)

    @Query("SELECT * FROM pokemon_list")
    fun getAll(): Flow<List<PokemonProfile>>
}