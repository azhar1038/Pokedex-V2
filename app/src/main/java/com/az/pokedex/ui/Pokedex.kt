package com.az.pokedex.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.az.pokedex.ui.view.pokemondetail.PokemonDetailView
import com.az.pokedex.ui.view.pokemonlist.PokemonListView
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
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
            PokemonListView(navController)
        }

        composable(
            route = "/pokemon_detail/{pokemonId}/{dominantColor}/{dominantOnColor}",
            arguments = listOf(
                navArgument("pokemonId"){ type = NavType.LongType },
                navArgument("dominantColor"){ type = NavType.IntType },
                navArgument("dominantOnColor"){ type = NavType.IntType },
            )
        ){
            val pokemonId: Long = it.arguments?.getLong("pokemonId") ?: 1
            val dominantColor: Int = it.arguments?.getInt("dominantColor") ?: 0
            val dominantOnColor: Int = it.arguments?.getInt("dominantOnColor") ?: 0
            PokemonDetailView(pokemonId, dominantColor, dominantOnColor)
        }
    }
}