package com.az.pokedex.ui.view.pokemonlist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonListView(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    Text("PokemonList")
}