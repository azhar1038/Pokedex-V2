package com.az.pokedex.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.az.pokedex.model.PokemonProfile

@Database(entities = [PokemonProfile::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}