package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.az.pokedex.model.remote.ResponsePokemon

@Composable
fun Abilities(
    pokemon: ResponsePokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ){
        pokemon.abilities.forEach {
            Text(it.ability.name)
        }
    }
}