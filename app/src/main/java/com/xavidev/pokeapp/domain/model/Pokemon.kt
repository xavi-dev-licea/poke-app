package com.xavidev.pokeapp.domain.model

import androidx.room.PrimaryKey
import com.xavidev.pokeapp.data.local.model.PokemonEntity
import java.io.Serializable

data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val number: Int,
    val image: String,
    val type: String,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val specialAttack: Int,
    val specialDefense: Int,
) : Serializable

fun Pokemon.toEntity() = PokemonEntity(
    id, name, number, image, type, hp, attack, defense, speed, specialAttack, specialDefense
)