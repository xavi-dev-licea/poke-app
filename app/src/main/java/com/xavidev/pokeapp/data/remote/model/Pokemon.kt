package com.xavidev.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("sprites") val image: Sprites,
    @SerializedName("stats") val stats: List<Stats>,
)