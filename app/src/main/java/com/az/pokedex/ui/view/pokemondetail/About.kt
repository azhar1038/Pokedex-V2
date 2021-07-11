package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.az.pokedex.R
import com.az.pokedex.model.remote.ResponsePokemon
import kotlin.math.round

@Composable
fun AboutColumn(
    resource: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = resource),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface.copy(0.5f),
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
        )
    }
}

@Composable
fun About(
    pokemon: ResponsePokemon,
    modifier: Modifier = Modifier,
) {
    val pokemonWeightInKg = remember {
        round(pokemon.weight * 100f) / 1000f
    }

    val pokemonHeightInMeters = remember {
        round(pokemon.height * 100f) / 1000f
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        AboutColumn(
            resource = R.drawable.ic_height,
            text = "$pokemonHeightInMeters m",
            modifier = Modifier.weight(1f),
        )
        AboutColumn(
            resource = R.drawable.ic_weight,
            text = "$pokemonWeightInKg kg",
            modifier = Modifier.weight(1f),
        )
    }
}