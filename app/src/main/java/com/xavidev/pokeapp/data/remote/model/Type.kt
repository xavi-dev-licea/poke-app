package com.xavidev.pokeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("type") val type: TypeName,
)

data class TypeName(
    @SerializedName("name") val name: String
)
