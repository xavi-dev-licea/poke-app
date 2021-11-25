package com.xavidev.pokeapp.data.remote.net

import com.xavidev.pokeapp.data.remote.model.Pokemon
import com.xavidev.pokeapp.data.remote.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonServices {

    @GET("pokemon/{name}/")
    suspend fun searchPokemon(@Path("name") search: String): Pokemon

    @GET("pokemon/?limit=10")
    suspend fun getRandomPokemons(@Query("offset") offset: Int): PokemonListResponse
}