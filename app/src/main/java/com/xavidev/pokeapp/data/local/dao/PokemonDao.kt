package com.xavidev.pokeapp.data.local.dao

import androidx.room.*
import com.xavidev.pokeapp.data.local.db.POKEMON_TABLE
import com.xavidev.pokeapp.data.local.model.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM $POKEMON_TABLE")
    suspend fun getAll(): List<PokemonEntity>

    @Query("SELECT * FROM $POKEMON_TABLE where name =:name")
    suspend fun search(name: String): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonEntity)

    @Delete
    suspend fun delete(pokemon: PokemonEntity): Int
}