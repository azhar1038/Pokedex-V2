package com.az.pokedex.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.ui.view.pokemondetail.PokemonDetailView
import com.az.pokedex.ui.view.pokemonlist.PokemonListView

@Composable
fun Pokedex() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "/pokemon_list"
    ){
        composable(
            route = "/pokemon_list"
        ){
            PokemonListView()
        }

        composable(
            route = "/pokemon_detail/{pokemonId}/{dominantColor}",
            arguments = listOf(
                navArgument("pokemonId"){ type = NavType.LongType },
                navArgument("dominantColor"){ type = NavType.IntType },
            )
        ){
            val pokemonId: Long = it.arguments?.getLong("pokemonId") ?: 1
            val dominantColor: Int = it.arguments?.getInt("dominantColor") ?: 0
            PokemonDetailView(pokemonId, dominantColor)
        }
    }
}