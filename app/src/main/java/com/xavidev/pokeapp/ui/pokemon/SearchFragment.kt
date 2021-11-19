package com.xavidev.pokeapp.ui.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentSearchBinding
import com.xavidev.pokeapp.utils.Status
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val factory = PokemonViewModel.PokemonFactory()
        viewModel = ViewModelProvider(this, factory).get(PokemonViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = viewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT)
                    .show()
                Status.SUCCESS -> Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT)
                    .show()
                Status.ERROR -> Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()

            }
        }
        lifecycleScope.launch {
            viewModel.getPokemonByName("pikachu")
        }
    }
}