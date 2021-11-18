package com.xavidev.pokeapp.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentPokedexBinding

class PokedexFragment : Fragment() {

    private var _binding: FragmentPokedexBinding? = null
    private val binding: FragmentPokedexBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }
}