package com.az.pokedex.ui.view.pokemondetail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.az.pokedex.model.remote.ResponsePokemon
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit,
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }
    ) {
        onClick()
    }
}

@ExperimentalPagerApi
@Composable
fun DetailTabs(
    pokemon: ResponsePokemon,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = 4)

    val tabNames = listOf(
        "Base Stats",
        "About",
        "Abilities",
        "Moves"
    )

    Column {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            divider = {},
            edgePadding = 12.dp,
            indicator = {
                TabRowDefaults.Indicator(
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, it)
                        .padding(horizontal = 24.dp)
                )
            },
        ) {
            tabNames.forEachIndexed { index, name ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .noRippleClickable {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        name,
                        textAlign = TextAlign.Center,
                        fontWeight = if (index == pagerState.currentPage) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                }
            }
        }
        HorizontalPager(state = pagerState) { page ->
            when (page) {
                0 -> BaseStats(pokemon, modifier)
                1 -> About(pokemon, modifier)
                2 -> Abilities(pokemon, modifier)
                3 -> Moves(pokemon, modifier)
            }
        }
//        when (tabIndex) {
//            0 -> BaseStats(pokemon, modifier)
//            1 -> About(pokemon, modifier)
//            2 -> Abilities(pokemon, modifier)
//            3 -> Moves(pokemon, modifier)
//            else -> BaseStats(pokemon, modifier)
//        }
    }
}