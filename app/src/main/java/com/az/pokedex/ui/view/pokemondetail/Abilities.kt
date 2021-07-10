package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.utils.parseName

@Composable
fun Ability(
    ability: String,
    background: Color,
    onBackground: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp,)
            .background(background, CircleShape)
            .padding(vertical = 12.dp)
    ) {
        Text(
            ability,
            color = onBackground,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

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
        Spacer(Modifier.height(24.dp))
        pokemon.abilities.forEach {
            Ability(
                ability = parseName(it.ability.name),
                background = MaterialTheme.colors.onSurface.copy(0.3f),
                onBackground = MaterialTheme.colors.onSurface,
            )
        }
    }
}