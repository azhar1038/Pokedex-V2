package com.az.pokedex.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.az.pokedex.model.PokemonProfile

@Composable
fun Pokedex(
    viewModel: PokedexViewModel = hiltViewModel()
) {
    val pokemonList: List<PokemonProfile> by viewModel.pokemonList.observeAsState(listOf())
    LazyColumn {
        items(pokemonList){ pokemon ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(pokemon.id.toString())
                Text(pokemon.name)
            }
        }
    }
}