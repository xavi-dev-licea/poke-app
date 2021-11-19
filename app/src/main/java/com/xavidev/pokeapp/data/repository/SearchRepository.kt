package com.xavidev.pokeapp.data.repository

import com.xavidev.pokeapp.domain.model.Pokemon

interface SearchRepository {

    suspend fun searchPokemon(name: String): Pokemon?
}