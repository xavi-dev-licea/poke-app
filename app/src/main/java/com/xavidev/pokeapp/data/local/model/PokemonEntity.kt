package com.xavidev.pokeapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xavidev.pokeapp.domain.model.Pokemon

@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val number: Int,
    val image: String,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    @ColumnInfo(name = "special-attack") val specialAttack: Int,
    @ColumnInfo(name = "special-attack") val specialDefense: Int,
)

fun PokemonEntity.toPokemon() = Pokemon(
    id, name, number, image, hp, attack, defense, speed, specialAttack, specialDefense
)