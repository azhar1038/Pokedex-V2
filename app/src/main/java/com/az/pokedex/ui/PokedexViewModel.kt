package com.az.pokedex.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.repository.pokemon.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    val pokemonList: LiveData<List<PokemonProfile>> = pokemonRepository.getPokemonList()

    init {
        viewModelScope.launch {
            pokemonRepository.refreshPokemonList()
        }
    }
}