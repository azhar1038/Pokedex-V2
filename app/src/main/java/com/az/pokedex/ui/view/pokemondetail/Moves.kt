package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.utils.parseName

@Composable
fun StaggeredGrid(
    count: Int,
    width: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
    ) { measurables, constraints ->
        val x = IntArray(count + 1) { 0 }
        val y = IntArray(count + 1) { 0 }

        var curX = 0
        var curY = 0
        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constraints)
            if (curX + placeable.width > width) {
                curY += placeable.height
                x[index] = 0
                y[index] = curY
                curX = placeable.width
            } else {
                x[index] = curX
                y[index] = curY
                curX += placeable.width
            }
            placeable
        }
        layout(
            width = width,
            height = curY + 100
        ) {
            placeables.mapIndexed { index, placeable ->
                placeable.placeRelative(
                    x = x[index],
                    y = y[index]
                )
            }
        }
    }
}

@Composable
fun Moves(
    pokemon: ResponsePokemon,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        BoxWithConstraints {
            StaggeredGrid(
                count = pokemon.moves.size,
                width = LocalDensity.current.run { maxWidth.toPx().toInt() },
            ) {
                pokemon.moves.forEach {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 4.dp)
                            .background(MaterialTheme.colors.onSurface.copy(0.3f), CircleShape)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            parseName(it.move.name),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}