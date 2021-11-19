package com.xavidev.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("front_default") val image: String
)
