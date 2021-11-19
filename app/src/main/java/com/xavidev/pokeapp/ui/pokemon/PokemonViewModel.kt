package com.xavidev.pokeapp.ui.pokemon

import android.view.View
import androidx.lifecycle.*
import com.xavidev.pokeapp.data.remote.RetrofitBuilder
import com.xavidev.pokeapp.data.remote.source.PokemonDataSource
import com.xavidev.pokeapp.data.repository.SearchRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.Response

class PokemonViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _pokemon = MutableLiveData<Response<Pokemon>>()
    val pokemon: LiveData<Response<Pokemon>> = _pokemon

    var searchHistoryContainer: LiveData<Int> = _pokemon.switchMap { pokemon ->
        val visibility = if (pokemon == null) {
            View.GONE
        } else {
            View.VISIBLE
        }
        MutableLiveData(visibility)
    }

    suspend fun getPokemonByName(name: String) {
        try {
            _pokemon.value = Response.Loading()
            val result = searchRepository.searchPokemon(name)
            result?.let { pokemon ->
                _pokemon.value = Response.Success(pokemon)
            }
        } catch (ex: Exception) {
            _pokemon.value = Response.Error(ex.localizedMessage)
        }
    }

    class PokemonFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
                return PokemonViewModel(
                    searchRepository = PokemonDataSource(RetrofitBuilder.pokemonRemoteApi)
                ) as T
            }
            throw Exception("No class supported")
        }
    }
}