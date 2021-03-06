package com.az.pokedex.ui.view.pokemonlist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.az.pokedex.model.DominantColor
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.repository.pokemon.PokemonRepository
import com.az.pokedex.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _pokemonList: Flow<Resource<List<PokemonProfile>>> = pokemonRepository.getPokemonList()

    val pokemonList = mutableStateOf<List<PokemonProfile>>(listOf())
    val filterList = mutableStateOf<List<Int>>(listOf())
    var dominantColor = listOf<MutableState<DominantColor?>>()

    var isLoading = mutableStateOf(true)
    var searchText = mutableStateOf("")
    var searchActive = mutableStateOf(false)

    init {
        viewModelScope.launch {
//            pokemonRepository.refreshPokemonList()
            _pokemonList.collect {
                when(it){
                    is Resource.Success -> {
                        Log.i("TAG", "PokemonList: Success")
                        pokemonList.value = it.data!!
                        filterList.value = (pokemonList.value.indices).toList()
                        dominantColor = pokemonList.value.map { mutableStateOf(null) }
                        isLoading.value = false
                    }
                    is Resource.Loading -> {
                        Log.i("TAG", "PokemonList: Loading...")
                        isLoading.value = true
                    }
                    is Resource.Error -> {
                        Log.i("TAG", "PokemonList: Error!!!")
                        isLoading.value = false
                    }
                }

            }
        }
    }

    fun search(text: String) {
        val match = text.lowercase()
        searchText.value = match
        searchActive.value = match.isNotEmpty()
        val newFilter = mutableListOf<Int>()
        pokemonList.value.forEachIndexed { index, pokemon ->
            if (pokemon.name.contains(match)) {
                newFilter.add(index)
            }
        }
        filterList.value = newFilter
    }

    suspend fun calculateDominantColor(context: Context, index: Int) {
        if (dominantColor[index].value != null) return

        val request = ImageRequest.Builder(context)
            .data(pokemonList.value[index].imageUrl)
            .build()

        val d: Drawable? = when (val result = Coil.execute(request)) {
            is SuccessResult -> result.drawable
            else -> null
        }

        d?.let {
            val bmp = (d as BitmapDrawable).bitmap.copy(
                Bitmap.Config.ARGB_8888,
                true
            )

            Palette.from(bmp).generate { palette ->
                palette?.dominantSwatch?.let {
                    dominantColor[index].value = DominantColor(
                        it.rgb,
                        it.bodyTextColor
                    )
                }
            }
        }

    }
}