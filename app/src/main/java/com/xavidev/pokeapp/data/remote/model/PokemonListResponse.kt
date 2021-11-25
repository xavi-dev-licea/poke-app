package com.xavidev.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonBase>
)

data class PokemonBase(
    @SerializedName("name") val name: String
)