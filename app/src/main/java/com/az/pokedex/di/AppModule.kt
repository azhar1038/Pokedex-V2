package com.az.pokedex.di

import com.az.pokedex.repository.pokemon.DefaultPokemonRepository
import com.az.pokedex.repository.pokemon.PokemonRepository
import com.az.pokedex.service.PokeApi
import com.az.pokedex.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object AppModule {
    @Provides
    fun providePokeApi(): PokeApi{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(PokeApi::class.java)
    }

    @Provides
    fun providePokemonRepository(
        pokeApi: PokeApi
    ): PokemonRepository = DefaultPokemonRepository(pokeApi)

}