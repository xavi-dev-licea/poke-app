package com.xavidev.pokeapp.data.repository

import androidx.lifecycle.LiveData
import com.xavidev.pokeapp.domain.model.Pokemon

interface LocalRepository {

    suspend fun getAll(): LiveData<List<Pokemon>>

    suspend fun search(name: String): LiveData<List<Pokemon>>

    suspend fun addPokemon(pokemon: Pokemon)

    suspend fun removePokemon(pokemon: Pokemon): Boolean

    suspend fun getOneById(id: Int): Int
}