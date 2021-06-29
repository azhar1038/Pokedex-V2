package com.az.pokedex.ui.view.pokemonlist

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import com.az.pokedex.model.PokemonProfile
import kotlin.math.ceil

@Composable
fun PokemonListView(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList: List<PokemonProfile> by viewModel.filteredPokemonList.collectAsState(listOf())

    val dominantColor = pokemonList.map {
        remember(it) { mutableStateOf<Color?>(null) }
    }

    val context = LocalContext.current

    fun getImageRequest(index: Int): ImageRequest {
        return ImageRequest.Builder(context)
            .data(pokemonList[index].imageUrl)
            .build()
    }

    Column {
        Row(
            modifier = Modifier.height(54.dp)
        ) {
            Text("Search")
        }
        if (pokemonList.isNotEmpty()) {
            LazyColumn {
                items(ceil(pokemonList.size / 2.0f).toInt()) { index ->
                    val first: Int = index * 2
                    val second: Int = index * 2 + 1
                    if (dominantColor[first].value == null || dominantColor[second].value == null) {
                        LaunchedEffect(index) {
                            val d1: Drawable? = viewModel.getDrawable(getImageRequest(first))
                            val d2: Drawable? = viewModel.getDrawable(getImageRequest(second))
                            d1?.let {
                                viewModel.calculateDominantColor(d1) { color ->
                                    dominantColor[first].value = color
                                }
                            }
                            d2?.let {
                                viewModel.calculateDominantColor(d2) { color ->
                                    dominantColor[second].value = color
                                }
                            }
                        }
                    }

                    Row {
                        PokemonCard(
                            pokemon = pokemonList[first],
                            background = dominantColor[first].value ?: Color.White,
                            modifier = Modifier.weight(1f)
                        ) {
                            //TODO: Navigate to detail page
                        }
                        PokemonCard(
                            pokemon = pokemonList[second],
                            background = dominantColor[second].value ?: Color.White,
                            modifier = Modifier.weight(1f)
                        ) {
                            //TODO: Navigate to detail page
                        }
                    }

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