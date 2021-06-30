package com.az.pokedex.ui.view.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.Coil
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.az.pokedex.model.PokemonProfile
import com.az.pokedex.repository.pokemon.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {
    val pokemonList: Flow<List<PokemonProfile>> = pokemonRepository.getPokemonList()

    private val _searchText = MutableLiveData("")

    var filteredPokemonList = pokemonList.map {
        it.filter { pokemon ->
            pokemon.name.lowercase().contains(_searchText.value.toString())
        }
    }

    init {
        viewModelScope.launch {
            pokemonRepository.refreshPokemonList()
        }
    }

    fun search(text: String){
        _searchText.value = text
    }

    suspend fun getDrawable(request: ImageRequest): Drawable?{
        return when(val result = Coil.execute(request)){
            is SuccessResult -> result.drawable
            else -> null
        }
    }

    fun calculateDominantColor(
        drawable: Drawable,
        onFinish: (Color) -> Unit
    ) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(
            Bitmap.Config.ARGB_8888,
            true
        )

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { color ->
                onFinish(Color(color))
            }
        }
    }
}