package com.az.pokedex.service.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.az.pokedex.model.PokemonProfile

@Database(entities = [PokemonProfile::class], version = 1, exportSchema = false)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

//    companion object {
//        @Volatile
//        private var INSTANCE: PokemonDatabase? = null
//
//        fun getInstance(context: Context): PokemonDatabase {
//            synchronized(this){
//                var instance = INSTANCE
//                if(instance == null){
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        PokemonDatabase::class.java,
//                        "pokemon_database"
//                    ).fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
}