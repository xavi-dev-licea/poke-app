package com.xavidev.pokeapp.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xavidev.pokeapp.data.local.db.DatabaseManager
import com.xavidev.pokeapp.data.local.source.PokemonSource
import com.xavidev.pokeapp.data.repository.LocalRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.ObservableViewModel
import java.lang.Exception

class DetailsViewModel(private val localRepository: LocalRepository) : ObservableViewModel() {

    fun savePokemon(pokemon: Pokemon) {
        //ur code here
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