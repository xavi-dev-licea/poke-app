package com.xavidev.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: Stat
)

data class Stat(
    @SerializedName("name") val name: String
)
