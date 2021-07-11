package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.utils.parseStatToAbbr
import com.az.pokedex.utils.parseStatToColor

@Composable
fun Stat(
    name: String,
    value: Int,
    maxValue: Int,
    color: Color,
    modifier: Modifier = Modifier,
    height: Dp = 4.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            value / maxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay,
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier.width(60.dp)
        ){
            Text(
                name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
            )
        }
        Box(
            modifier = modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(curPercent.value)
                    .height(height)
                    .clip(CircleShape)
                    .background(color)
                    .padding(horizontal = 8.dp)
            )
        }
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.width(40.dp)
        ){
            Text(
                (curPercent.value * maxValue).toInt().toString(),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}

@Composable
fun BaseStats(
    pokemon: ResponsePokemon,
    modifier: Modifier = Modifier
) {
    val maxBaseStat = remember {
        pokemon.stats.maxOf { it.baseStat }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(top = 24.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        for (i in pokemon.stats.indices) {
            val stat = pokemon.stats[i]
            Stat(
                name = parseStatToAbbr(stat),
                value = stat.baseStat,
                maxValue = maxBaseStat,
                color = MaterialTheme.colors.onSurface.copy(0.5f),
                animDelay = i * 100,
                modifier = modifier,
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}