package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.az.pokedex.model.remote.ResponsePokemon

@Composable
fun DetailTabs(
    pokemon: ResponsePokemon,
    modifier: Modifier = Modifier
) {
    var tabIndex by remember {
        mutableStateOf(0)
    }

    val tabNames = listOf(
        "About",
        "Base Stats",
        "Abilities",
        "Moves"
    )

    Column {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            divider = {},
            indicator = {
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .tabIndicatorOffset(it[tabIndex])
                        .padding(horizontal = 24.dp)
                )
            },
            modifier = Modifier.padding(horizontal = 24.dp),

        ) {
            tabNames.forEachIndexed { index, name ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable {
                            tabIndex = index
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        name,
                        textAlign = TextAlign.Center,
                        fontWeight = if(index == tabIndex) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                }
            }
        }
        when (tabIndex) {
            0 -> About(pokemon, modifier)
            1 -> BaseStats(pokemon, modifier)
            2 -> Abilities(pokemon, modifier)
            3 -> Moves(pokemon, modifier)
            else -> About(pokemon, modifier)
        }
    }
}