package com.xavidev.pokeapp.data.local.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xavidev.pokeapp.data.local.dao.PokemonDao
import com.xavidev.pokeapp.data.local.model.toPokemon
import com.xavidev.pokeapp.data.repository.LocalRepository
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.domain.model.toEntity

class PokemonSource(private val pokemonDao: PokemonDao) : LocalRepository {
    override suspend fun getAll(): LiveData<List<Pokemon>> {
        return MutableLiveData(pokemonDao.getAll().map { it.toPokemon() })
    }

    override suspend fun search(name: String): LiveData<List<Pokemon>> {
        return MutableLiveData(pokemonDao.search(name).map { it.toPokemon() })
    }

    override suspend fun addPokemon(pokemon: Pokemon) {
        pokemonDao.insert(pokemon.toEntity())
    }

    override suspend fun removePokemon(pokemon: Pokemon): Boolean {
        return pokemonDao.delete(pokemon.toEntity()) == 1
    }

    override suspend fun getOneById(id: Int): Int {
        return pokemonDao.getOneById(id)
    }
}