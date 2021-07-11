package com.az.pokedex.di

import android.content.Context
import androidx.room.Room
import com.az.pokedex.service.database.PokemonDao
import com.az.pokedex.service.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PokemonDatabase{
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(
        pokemonDatabase: PokemonDatabase
    ): PokemonDao = pokemonDatabase.pokemonDao()
}