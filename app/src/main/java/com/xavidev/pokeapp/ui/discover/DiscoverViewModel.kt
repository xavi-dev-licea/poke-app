package com.xavidev.pokeapp.ui.discover

import androidx.lifecycle.*
import com.xavidev.pokeapp.data.remote.RetrofitBuilder
import com.xavidev.pokeapp.data.remote.source.PokemonDataSource
import com.xavidev.pokeapp.data.repository.DiscoverRepository
import com.xavidev.pokeapp.data.repository.SearchRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val discoverRepository: DiscoverRepository,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<Response<List<Pokemon>>>()
    val pokemonList: LiveData<Response<List<Pokemon>>> = _pokemonList

    fun getRandomPokemons() = viewModelScope.launch(Dispatchers.Main) {
        val pokemons = mutableListOf<Pokemon>()
        try {
            _pokemonList.value = Response.Loading()
            val result = discoverRepository.getRandomPokemon(generateRandomOffset())
            result.value?.let { list ->
                list.forEach { pokemon ->
                    getPokemonByName(pokemon.name)?.let { value ->
                        pokemons.add(value)
                    }
                }
                _pokemonList.value = Response.Success(pokemons)
            }
        } catch (ex: Exception) {
            _pokemonList.value = Response.Error(ex.localizedMessage)
        }
    }

    private suspend fun getPokemonByName(name: String): Pokemon? {
        var pokemonDetails: Pokemon? = null
        try {
            val result = searchRepository.searchPokemon(name)
            result?.let { pokemon -> pokemonDetails = pokemon }
        } catch (ex: Exception) {

        }
        return pokemonDetails
    }

    private fun generateRandomOffset(): Int = (1..100).random()

    class DiscoverViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DiscoverViewModel::class.java)) {
                return DiscoverViewModel(
                    discoverRepository = PokemonDataSource(RetrofitBuilder.pokemonRemoteApi),
                    searchRepository = PokemonDataSource(RetrofitBuilder.pokemonRemoteApi)
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}