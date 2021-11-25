package com.xavidev.pokeapp.data.repository

import androidx.lifecycle.LiveData
import com.xavidev.pokeapp.data.remote.model.PokemonBase

interface DiscoverRepository {

    suspend fun getRandomPokemon(offset: Int): LiveData<List<PokemonBase>>
}