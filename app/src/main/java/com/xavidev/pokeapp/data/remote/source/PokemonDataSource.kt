package com.xavidev.pokeapp.data.remote.source

import com.xavidev.pokeapp.data.remote.net.PokemonServices
import com.xavidev.pokeapp.data.repository.SearchRepository
import com.xavidev.pokeapp.domain.model.Pokemon

class PokemonDataSource(private val pokemonServices: PokemonServices) : SearchRepository {
    override suspend fun searchPokemon(name: String): Pokemon? {
        val pokemon = pokemonServices.searchPokemon(name)

        return Pokemon(
            id = pokemon.id,
            name = pokemon.name,
            number = pokemon.id,
            image = pokemon.image.image,
            type = pokemon.types[0].type.name,
            hp = pokemon.stats[0].baseStat,
            attack = pokemon.stats[1].baseStat,
            defense = pokemon.stats[2].baseStat,
            speed = pokemon.stats[3].baseStat,
            specialAttack = pokemon.stats[4].baseStat,
            specialDefense = pokemon.stats[5].baseStat,
        )
    }
}