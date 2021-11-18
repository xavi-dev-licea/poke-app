package com.xavidev.pokeapp.ui.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentDetailsBinding
import com.xavidev.pokeapp.databinding.FragmentDiscoveryBinding

class DiscoveryFragment : Fragment() {

    private var _binding: FragmentDiscoveryBinding? = null
    private val binding: FragmentDiscoveryBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding.root
    }
}