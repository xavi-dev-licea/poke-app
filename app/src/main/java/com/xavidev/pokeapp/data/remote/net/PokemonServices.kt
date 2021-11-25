package com.xavidev.pokeapp.data.remote.net

import com.xavidev.pokeapp.data.remote.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonServices {

    @GET("pokemon/{name}/")
    suspend fun searchPokemon(@Path("name") search: String): Pokemon

    @GET("pokemon")
    suspend fun getRandomPokemons(): List<Pokemon>
}