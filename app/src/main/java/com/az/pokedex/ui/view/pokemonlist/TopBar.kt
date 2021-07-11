package com.az.pokedex.ui.view.pokemonlist

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(
    searchHint: String,
    modifier: Modifier = Modifier,
    initialSearchText: String = "",
    initiallyActive: Boolean = false,
    onChange: (String) -> Unit = {}
) {
    var isSearchActive by remember {
        mutableStateOf(initiallyActive)
    }

    val titleAlpha: Float by animateFloatAsState(
        if (isSearchActive) 0f else 1f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearOutSlowInEasing,
        )
    )

    val searchIconOffsetX by animateDpAsState(
        if (isSearchActive) (-16).dp else 0.dp
    )

    var searchText: String by remember {
        mutableStateOf(initialSearchText)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colors.surface)
            .padding(8.dp)
    ) {
        Text(
            "Pokedex",
            fontSize = 28.sp,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 16.dp)
                .alpha(titleAlpha)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(1 - titleAlpha)
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterEnd)
                .alpha(1 - titleAlpha)
        ) {
            BasicTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    onChange(it)
                },
                textStyle = TextStyle(
                    fontSize = 16.sp
                ),
                enabled = isSearchActive,
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x55aaaaaa), shape = CircleShape)
                    .padding(
                        start = 20.dp,
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 50.dp
                    )
            )
            if (isSearchActive) {
                Text(
                    text = if (searchText.isEmpty()) searchHint else "",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 20.dp)
                )
            }
        }

        IconButton(
            onClick = {
                if(isSearchActive){
                    searchText = ""
                    onChange("")
                }
                isSearchActive = !isSearchActive
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = searchIconOffsetX)
        ) {
            Icon(
                if (!isSearchActive) Icons.Filled.Search else Icons.Filled.Clear,
                contentDescription = "Search button",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopBar() {
    TopBar(searchHint = "Search")
}