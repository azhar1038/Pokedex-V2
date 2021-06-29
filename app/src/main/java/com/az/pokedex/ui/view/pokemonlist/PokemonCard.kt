package com.az.pokedex.ui.view.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.az.pokedex.model.PokemonProfile
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun PokemonCard(
    pokemon: PokemonProfile,
    background: Color,
    modifier: Modifier = Modifier,
    onPressed: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        background.copy(0.7f),
                        background.copy(0.4f)
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