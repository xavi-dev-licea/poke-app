package com.xavidev.pokeapp.ui.pokedex

import androidx.lifecycle.*
import com.xavidev.pokeapp.data.local.db.DatabaseManager
import com.xavidev.pokeapp.data.local.source.PokemonSource
import com.xavidev.pokeapp.data.repository.LocalRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokedexViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val _allPokemons = MutableLiveData<List<Pokemon>>()
    val allPokemons: LiveData<List<Pokemon>> = _allPokemons

    fun getPokedex() = viewModelScope.launch(Dispatchers.Main) {
        _allPokemons.value = localRepository.getAll().value
    }

    fun searchPokemonByName(query: String) = viewModelScope.launch(Dispatchers.Main) {
        _allPokemons.value = localRepository.search(query).value
    }

    class PokedexViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PokedexViewModel::class.java)) {
                return PokedexViewModel(
                    localRepository = PokemonSource(DatabaseManager.instance.database.pokemonDao())
                ) as T
            }
            throw Exception("Class not supported")
        }
    }
}