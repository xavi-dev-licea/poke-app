package com.xavidev.pokeapp.utils

import com.xavidev.pokeapp.R

object ColorUtils {
    fun getColorByType(name: String): Int {

        val colors = mapOf(
            R.color.normal_color to "normal",
            R.color.fire_color to "fire",
            R.color.water_color to "water",
            R.color.electric_color to "electric",
            R.color.grass_color to "grass",
            R.color.ice_color to "ice",
            R.color.fighting_color to "fighting",
            R.color.poison_color to "poison",
            R.color.ground_color to "ground",
            R.color.flying_color to "flying",
            R.color.psychic_color to "psychic",
            R.color.bug_color to "bug",
            R.color.rock_color to "rock",
            R.color.ghost_color to "ghost",
            R.color.dragon_color to "dragon",
            R.color.dark_color to "dark",
            R.color.steel_color to "steel",
            R.color.fairy_color to "fairy",
        )

        return colors.filterValues { value -> value == name }.keys.elementAt(0)
    }
}