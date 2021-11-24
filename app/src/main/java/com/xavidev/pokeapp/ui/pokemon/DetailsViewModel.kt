package com.xavidev.pokeapp.ui.pokemon

import androidx.lifecycle.*
import com.xavidev.pokeapp.data.local.db.DatabaseManager
import com.xavidev.pokeapp.data.local.source.PokemonSource
import com.xavidev.pokeapp.data.repository.LocalRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.ObservableViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailsViewModel(private val localRepository: LocalRepository) : ObservableViewModel() {

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> = _count

    fun savePokemon(pokemon: Pokemon) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.addPokemon(pokemon)
    }

    fun removePokemon(pokemon: Pokemon) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.removePokemon(pokemon)
    }

    fun getOneById(id: Int) = viewModelScope.launch(Dispatchers.Main) {
        _count.value = localRepository.getOneById(id)
    }

    class DetailsViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                return DetailsViewModel(
                    localRepository = PokemonSource(DatabaseManager.instance.database.pokemonDao())
                ) as T
            }
            throw Exception("Class type not supported")
        }
    }
}