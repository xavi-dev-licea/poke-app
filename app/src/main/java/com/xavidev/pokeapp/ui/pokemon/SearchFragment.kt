package com.xavidev.pokeapp.ui.pokemon

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentSearchBinding
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.Status
import com.xavidev.pokeapp.utils.hideKeyboard
import com.xavidev.pokeapp.utils.toast
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private lateinit var activityContext: Activity

    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val factory = PokemonViewModel.PokemonFactory()
        viewModel = ViewModelProvider(this, factory).get(PokemonViewModel::class.java)

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
        Navigation.findNavController(binding.root).navigate(R.id.navigateToDetailsFragment)
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