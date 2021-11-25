package com.xavidev.pokeapp.ui.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xavidev.pokeapp.BR
import com.xavidev.pokeapp.databinding.DiscoverPokemonItemBinding
import com.xavidev.pokeapp.databinding.PokedexPokemonItemBinding
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.ColorUtils

class DiscoverAdapter(private val listener: (Pokemon) -> Unit) :
    ListAdapter<Pokemon, DiscoverAdapter.DiscoverViewHolder>(PokedexDiffCalback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: DiscoverPokemonItemBinding =
            DiscoverPokemonItemBinding.inflate(layoutInflater, parent, false)
        return DiscoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val item: Pokemon? = getItem(position)
        item?.let { pokemon ->
            holder.bind(pokemon, listener)
        }
    }

    inner class DiscoverViewHolder(private val binding: DiscoverPokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon, listener: (Pokemon) -> Unit) {
            val colorId = ColorUtils.getColorByType(item.type)
            val color = ContextCompat.getColor(binding.root.context, colorId)
            binding.setVariable(BR.pokemon, item)
            binding.setVariable(BR.background, color)
            binding.discoveryItem.setOnClickListener { listener(item) }
        }
    }

    companion object {
        object PokedexDiffCalback : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean =
                oldItem == newItem
        }
    }
}