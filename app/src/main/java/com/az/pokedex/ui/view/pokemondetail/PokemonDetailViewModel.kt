package com.az.pokedex.ui.view.pokemondetail

import androidx.lifecycle.ViewModel
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.repository.pokemon.PokemonRepository
import com.az.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    suspend fun getPokemonInfo(id: Long): Resource<ResponsePokemon>{
        return pokemonRepository.getPokemonInfo(id.toInt())
    }
}