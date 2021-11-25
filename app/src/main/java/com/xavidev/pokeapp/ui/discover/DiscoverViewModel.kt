package com.xavidev.pokeapp.ui.discover

import androidx.lifecycle.*
import com.xavidev.pokeapp.data.remote.RetrofitBuilder
import com.xavidev.pokeapp.data.remote.source.PokemonDataSource
import com.xavidev.pokeapp.data.repository.DiscoverRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoverViewModel(private val discoverRepository: DiscoverRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<Response<List<Pokemon>>>()
    val pokemonList: LiveData<Response<List<Pokemon>>> = _pokemonList

    fun getRandomPokemons() = viewModelScope.launch(Dispatchers.Main) {
        try {
            _pokemonList.value = Response.Loading()
            val result = discoverRepository.getRandomPokemon()
            result.value?.let { list ->
                _pokemonList.value = Response.Success(list)
            }
        } catch (ex: Exception) {
            _pokemonList.value = Response.Error(ex.localizedMessage)
        }
    }

    class DiscoverViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DiscoverViewModel::class.java)) {
                return DiscoverViewModel(
                    discoverRepository = PokemonDataSource(RetrofitBuilder.pokemonRemoteApi)
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}