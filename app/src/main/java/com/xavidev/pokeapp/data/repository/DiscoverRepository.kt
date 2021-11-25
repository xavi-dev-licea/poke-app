package com.xavidev.pokeapp.data.repository

import androidx.lifecycle.LiveData
import com.xavidev.pokeapp.domain.model.Pokemon

interface DiscoverRepository {

    suspend fun getRandomPokemon(): LiveData<List<Pokemon>>
}