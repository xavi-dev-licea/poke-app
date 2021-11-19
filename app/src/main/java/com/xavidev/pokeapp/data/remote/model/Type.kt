package com.xavidev.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("name") val name: String,
)
