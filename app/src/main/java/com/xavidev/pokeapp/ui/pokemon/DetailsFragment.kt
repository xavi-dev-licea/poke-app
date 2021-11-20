package com.xavidev.pokeapp.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.pokeapp.databinding.FragmentDetailsBinding
import com.xavidev.pokeapp.domain.model.Pokemon

class DetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var myPokemon: Pokemon

    companion object {
        const val TAG = "DetailsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        myPokemon = arguments?.getSerializable("pokemon") as Pokemon
        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            pokemon = myPokemon
        }

        setupListeners()

        dialog?.setCancelable(false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = DetailsViewModel.DetailsViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    private fun setupListeners() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
    }
}