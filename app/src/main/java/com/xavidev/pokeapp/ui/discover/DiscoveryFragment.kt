package com.xavidev.pokeapp.ui.discover

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentDetailsBinding
import com.xavidev.pokeapp.databinding.FragmentDiscoveryBinding
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.ui.pokemon.DetailsFragment
import com.xavidev.pokeapp.utils.Status
import com.xavidev.pokeapp.utils.toast

class DiscoveryFragment : Fragment() {

    private var _binding: FragmentDiscoveryBinding? = null
    private val binding: FragmentDiscoveryBinding get() = _binding!!
    private lateinit var viewModel: DiscoverViewModel

    private val discoverAdapter = DiscoverAdapter(object : (Pokemon) -> Unit {
        override fun invoke(pokemon: Pokemon) {
            navigateToDetailsFragment(pokemon)
        }
    })

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

        getPokemons()

        setupList()
    }

    private fun setupList() {
        binding.recyclerDiscover.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = discoverAdapter
        }
    }

    private fun getPokemons() {
        viewModel.pokemonList.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> requireActivity().toast("Loading...")
                Status.SUCCESS -> response.data?.let { list -> discoverAdapter.submitList(list) }
                Status.ERROR -> response.message?.let { message ->
                    Log.d("ERROR", message)
                    requireActivity().toast(message)
                }
            }
        }
    }

    private fun navigateToDetailsFragment(pokemon: Pokemon) {
        val bundle = Bundle()
        bundle.putSerializable("pokemon", pokemon)
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = bundle
        detailsFragment.show(requireActivity().supportFragmentManager, DetailsFragment.TAG)
    }
}