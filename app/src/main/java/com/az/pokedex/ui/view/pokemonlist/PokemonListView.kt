package com.az.pokedex.ui.view.pokemonlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.az.pokedex.R
import com.google.accompanist.insets.systemBarsPadding
import kotlin.math.ceil

@Composable
fun PokemonListView(
    navController: NavController,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .systemBarsPadding()
    ) {
        TopBar(
            searchHint = "Search among ${viewModel.pokemonList.value.size} pokemons",
            initialSearchText = viewModel.searchText.value,
            initiallyActive = viewModel.searchActive.value
        ) {
            viewModel.search(it)
        }
        if (viewModel.isLoading.value){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
        else if (viewModel.filterList.value.isNotEmpty()) {
            LazyColumn(
                state = scrollState
            ) {
                items(ceil(viewModel.filterList.value.size / 2.0f).toInt()) { index ->
                    val firstIndex: Int = index * 2
                    val secondIndex: Int = index * 2 + 1
                    val first: Int = viewModel.filterList.value[firstIndex]
                    var second: Int? = null
                    if (secondIndex < viewModel.filterList.value.size) {
                        second = viewModel.filterList.value[secondIndex]
                    }
                    LaunchedEffect(first) {
                        viewModel.calculateDominantColor(context, first)
                    }
                    second?.let {
                        LaunchedEffect(second) {
                            viewModel.calculateDominantColor(context, second)
                        }
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        val pokemonOne = viewModel.pokemonList.value[first]
                        val dcOne = viewModel.dominantColor[first].value
                        PokemonCard(
                            pokemon = pokemonOne,
                            dominantColor = dcOne,
                            modifier = Modifier.weight(1f)
                        ) {
                            val bg: Int = dcOne?.background ?: 1
                            val onBg: Int = dcOne?.onBackground ?: 0
                            navController.navigate(
                                "/pokemon_detail/${pokemonOne.id}/$bg/$onBg"
                            )
                        }
                        if (second != null) {
                            val pokemonTwo = viewModel.pokemonList.value[second]
                            val dcTwo = viewModel.dominantColor[second].value
                            PokemonCard(
                                pokemon = pokemonTwo,
                                dominantColor = dcTwo,
                                modifier = Modifier.weight(1f)
                            ) {
                                val bg: Int = dcTwo?.background ?: 1
                                val onBg: Int = dcTwo?.onBackground ?: 0
                                navController.navigate(
                                    "/pokemon_detail/${pokemonTwo.id}/$bg/$onBg"
                                )
                            }
                        } else {
                            Spacer(Modifier.weight(1f))
                        }

                    }

                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pokeball),
                        tint = Color(0xffdddddd),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(Modifier.height(24.dp))
                    Text(
                        "No pokemon found",
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}

//@Composable
//fun PokemonRow(
//    first: PokemonProfile,
//    firstBg: Color,
//    second: PokemonProfile,
//    secondBg: Color,
//    onClick
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier.padding(horizontal = 8.dp)
//    ) {
//
//    }
//}