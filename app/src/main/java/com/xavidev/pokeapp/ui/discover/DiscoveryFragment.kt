package com.xavidev.pokeapp.ui.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentDetailsBinding
import com.xavidev.pokeapp.databinding.FragmentDiscoveryBinding
import com.xavidev.pokeapp.utils.Status
import com.xavidev.pokeapp.utils.toast

class DiscoveryFragment : Fragment() {

    private var _binding: FragmentDiscoveryBinding? = null
    private val binding: FragmentDiscoveryBinding get() = _binding!!
    private lateinit var viewModel: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory = DiscoverViewModel.DiscoverViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DiscoverViewModel::class.java)

        _binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getRandomPokemons()

        setupList()
    }

    private fun setupList() {
        viewModel.pokemonList.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> requireActivity().toast("Loading...")
                Status.SUCCESS -> response.data?.let { list ->
                    requireActivity().toast("$list")
                }
                Status.ERROR -> response.message?.let { message ->
                    requireActivity().toast(message)
                }
            }
        }
    }
}