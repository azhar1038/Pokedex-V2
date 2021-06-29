package com.az.pokedex.ui.view.pokemonlist

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import com.az.pokedex.R
import com.az.pokedex.model.PokemonProfile
import com.google.accompanist.coil.rememberCoilPainter
import kotlin.math.ceil

@Composable
fun PokemonListView(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val pokemonList: List<PokemonProfile> by viewModel.filteredPokemonList.collectAsState(initial = listOf())
    if (pokemonList.isNotEmpty()) {
        LazyColumn {
            items(ceil(pokemonList.size / 2.0f).toInt()) { index ->
                PokemonRow(
                    pokemonOne = pokemonList[index * 2],
                    pokemonTwo = pokemonList[index * 2 + 1]
                )
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonProfile,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    var dominantColor by remember {
        mutableStateOf(Color.White)
    }

    var drawable by remember {
        mutableStateOf<Drawable?>(null)
    }

    val request = ImageRequest.Builder(context)
        .data(pokemon.imageUrl)
        .crossfade(true)
        .build()

    LaunchedEffect(key1 = pokemon.id) {
        drawable = viewModel.getDrawable(request)
        drawable?.let {
            viewModel.calculateDominantColor(it) { color ->
                dominantColor = color
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(8.dp)
//            .shadow(5.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
//                        Color(0xffff8a65),
//                        Color(0xffffccbc)
                        dominantColor.copy(0.7f),
                        dominantColor.copy(0.4f)
                    )
                )
            )
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_pokeball),
            tint = Color(0x77ffffff),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 20.dp, y = 20.dp)
        )

        Image(
            painter = rememberCoilPainter(
                pokemon.imageUrl,
                fadeIn = true,
                fadeInDurationMs = 250,
                previewPlaceholder = R.drawable.ic_pokeball
            ),
            contentDescription = "${pokemon.name} Image",
            alignment = Alignment.BottomEnd,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.BottomEnd)
                .offset(12.dp, 12.dp)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                pokemon.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Text(
                "# ${pokemon.id}",
                color = Color.Black,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
fun PokemonRow(
    pokemonOne: PokemonProfile,
    pokemonTwo: PokemonProfile,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        PokemonCard(pokemon = pokemonOne, Modifier.weight(1f))
        PokemonCard(pokemon = pokemonTwo, Modifier.weight(1f))
    }
}