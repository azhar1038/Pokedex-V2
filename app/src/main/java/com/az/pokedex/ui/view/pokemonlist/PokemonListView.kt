package com.az.pokedex.ui.view.pokemonlist

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.*
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
    val context = LocalContext.current

    Column {
        TopBar(searchHint = "Search among ${viewModel.pokemonList.value.size} pokemons"){
            viewModel.search(it)
        }
        if (viewModel.filterList.value.isNotEmpty()) {
            LazyColumn {
                items(ceil(viewModel.filterList.value.size / 2.0f).toInt()) { index ->
                    val firstIndex: Int = index * 2
                    val secondIndex: Int = index * 2 + 1
                    val first: Int = viewModel.filterList.value[firstIndex]
                    var second: Int? = null
                    if(secondIndex < viewModel.filterList.value.size){
                        second = viewModel.filterList.value[secondIndex]
                    }
                    LaunchedEffect(first) {
                        viewModel.calculateDominantColor(context, first)
                    }
                    second?.let {
                        LaunchedEffect(second){
                            viewModel.calculateDominantColor(context, second)
                        }
                    }
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        PokemonCard(
                            pokemon = viewModel.pokemonList.value[first],
                            dominantColor = viewModel.dominantColor[first].value,
                            modifier = Modifier.weight(1f)
                        ) {
                            //TODO: Navigate to detail page
                        }
                        if(second != null){
                            PokemonCard(
                                pokemon = viewModel.pokemonList.value[second],
                                dominantColor = viewModel.dominantColor[second].value,
                                modifier = Modifier.weight(1f)
                            ) {
                                //TODO: Navigate to detail page
                            }
                        }else{
                            Spacer(Modifier.weight(1f))
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