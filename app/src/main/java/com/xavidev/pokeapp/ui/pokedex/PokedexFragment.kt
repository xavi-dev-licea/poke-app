package com.xavidev.pokeapp.ui.pokedex

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentPokedexBinding
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.ui.pokemon.DetailsFragment
import com.xavidev.pokeapp.utils.toast

class PokedexFragment : Fragment() {

    private var _binding: FragmentPokedexBinding? = null
    private val binding: FragmentPokedexBinding get() = _binding!!
    private lateinit var viewModel: PokedexViewModel
    private val pokedexAdapter = PokedexAdapter(object : (Pokemon, Int) -> Unit {
        override fun invoke(pokemon: Pokemon, pos: Int) {
            navigateToDetailsFragment(pokemon)
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = PokedexViewModel.PokedexViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(PokedexViewModel::class.java)

        viewModel.getPokedex()

        observers()

        setList()
    }

    private fun setList() {
        binding.recyclerPokedex.apply {
            adapter = pokedexAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observers() {
        viewModel.allPokemons.observe(viewLifecycleOwner) { pokemons ->
            pokedexAdapter.submitList(pokemons)
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