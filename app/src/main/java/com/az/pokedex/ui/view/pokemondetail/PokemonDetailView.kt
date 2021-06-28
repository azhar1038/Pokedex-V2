package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PokemonDetailView(
    pokemonId: Long,
    dominantColor: Int,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    Text("PokemonDetail")
}