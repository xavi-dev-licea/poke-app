package com.xavidev.pokeapp.ui.pokemon

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.xavidev.pokeapp.databinding.FragmentSearchBinding
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.Status
import com.xavidev.pokeapp.utils.hideKeyboard
import com.xavidev.pokeapp.utils.toast
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private lateinit var activityContext: Context

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val factory = SearchViewModel.PokemonFactory()
        viewModel = ViewModelProvider(this, factory).get(SearchViewModel::class.java)

        activityContext = requireActivity()

        _binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observers()
        searchPokemonByName()
    }

    private fun observers() {
        viewModel.pokemon.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> activityContext.toast("Loading...")
                Status.SUCCESS -> {
                    activityContext.hideKeyboard()
                    response.data?.let { pokemon ->
                        navigateToDetailsFragment(pokemon)
                    }
                }
                Status.ERROR -> activityContext.toast(response.message.toString())
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

    private fun searchPokemonByName() {
        binding.edtSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchString = binding.edtSearch.text.toString()
                lifecycleScope.launch {
                    viewModel.getPokemonByName(searchString)
                }
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }
}