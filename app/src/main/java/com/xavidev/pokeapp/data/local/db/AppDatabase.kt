package com.xavidev.pokeapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xavidev.pokeapp.data.local.dao.PokemonDao
import com.xavidev.pokeapp.data.local.model.PokemonEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "poke_database"
const val EXPORT_SCHEMA = false
const val POKEMON_TABLE = "pokemon"

@Database(
    entities = [PokemonEntity::class],
    version = DATABASE_VERSION,
    exportSchema = EXPORT_SCHEMA
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}