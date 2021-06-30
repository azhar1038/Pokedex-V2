package com.az.pokedex.ui.view.pokemonlist

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.az.pokedex.R
import com.az.pokedex.model.DominantColor
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.utils.parsePokemonName
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun PokemonCard(
    pokemon: PokemonProfile,
    dominantColor: DominantColor?,
    modifier: Modifier = Modifier,
    defaultBackground: Color = Color.LightGray,
    defaultOnBackground: Color = Color.Black,
    onPressed: () -> Unit = {},
) {
    val background by animateColorAsState(
        dominantColor?.background ?: defaultBackground,
        tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    )

    val onBackground by animateColorAsState(
        dominantColor?.onBackground ?: defaultOnBackground,
        tween(
            durationMillis = 300,
            easing = LinearOutSlowInEasing
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        background,
                        background.copy(0.7f)
                    )
                )
            )
            .clickable {
                onPressed()
            }
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
                fadeInDurationMs = 600,
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
                parsePokemonName(pokemon.name),
                color = onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Text(
                "#${pokemon.id}",
                color = onBackground,
                fontSize = 16.sp,
            )
        }
    }
}