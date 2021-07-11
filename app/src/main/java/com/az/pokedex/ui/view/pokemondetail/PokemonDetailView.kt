package com.az.pokedex.ui.view.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.az.pokedex.R
import com.az.pokedex.model.remote.ResponsePokemon
import com.az.pokedex.utils.Resource
import com.az.pokedex.utils.parseName
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun PokemonDetailView(
    pokemonId: Long,
    dominantColor: Int,
    dominantOnColor: Int,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemon = produceState<Resource<ResponsePokemon>>(
        initialValue = Resource.Loading()
    ) {
        value = viewModel.getPokemonInfo(pokemonId)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(dominantColor).copy(0.4f),
                        Color(dominantColor).copy(0.7f)
                    ),
                )
            )
            .systemBarsPadding()
    ) {
        Icon(
            painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            tint = Color(0x77ffffff),
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopEnd)
                .offset(40.dp, (50).dp)
        )
        when (pokemon) {
            is Resource.Success -> {
                PokemonDetail(
                    pokemon = pokemon.data!!,
                    onBackground = Color(dominantOnColor)
                )
            }
            is Resource.Error -> {
                Text("Error")
            }
            is Resource.Loading -> {
                CircularProgressIndicator(
                    color = Color(dominantOnColor),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun PokemonDetail(
    pokemon: ResponsePokemon,
    onBackground: Color,
) {
    Box {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        parseName(pokemon.name),
                        fontSize = 32.sp,
                        color = onBackground,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        "#${pokemon.id}",
                        fontSize = 20.sp,
                        color = onBackground,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(Modifier.height(8.dp))
                pokemon.types.forEach { type ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .height(30.dp)
                            .width(100.dp)
                            .background(
                                onBackground.copy(0.2f),
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            type.type.name.replaceFirstChar { it.uppercaseChar() },
                            color = onBackground,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        )
                    )
            ) {
                Column {
                    Spacer(Modifier.height(40.dp))
                    DetailTabs(pokemon)
                }
            }
        }
        Image(
            painter = rememberCoilPainter(
                pokemon.sprites.other.officialArtwork.frontDefault,
                fadeIn = true,
                fadeInDurationMs = 300,
            ),
            contentDescription = "${pokemon.name} Image",
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.TopCenter)
                .offset(y = 100.dp)
        )
    }
}