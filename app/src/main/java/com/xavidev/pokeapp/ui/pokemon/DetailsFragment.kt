package com.xavidev.pokeapp.ui.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.FragmentDetailsBinding
import com.xavidev.pokeapp.domain.model.Pokemon
import com.xavidev.pokeapp.utils.ColorUtils
import com.xavidev.pokeapp.utils.toast

class DetailsFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var myPokemon: Pokemon

    companion object {
        const val TAG = "DetailsFragment"
    }

    private var isPokemonAdded: Boolean = false
    private var actionResource: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        myPokemon = arguments?.getSerializable("pokemon") as Pokemon
        val colorId = ColorUtils.getColorByType(myPokemon.type)
        val color = ContextCompat.getColor(requireContext(), colorId)

        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            pokemon = myPokemon
            background = color
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.setCancelable(false)
        val factory = DetailsViewModel.DetailsViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)

        viewModel.getOneById(myPokemon.id)

        setupListeners()

        observers()
    }

    private fun setupListeners() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }
        binding.imgAdd.setOnClickListener { handleAction() }
    }

    private fun observers() {
        viewModel.count.observe(this) { count ->
            isPokemonAdded = count == 1
            actionResource = if (isPokemonAdded) R.drawable.ic_remove else R.drawable.ic_add_outline
            binding.imgAdd.setImageResource(actionResource!!)
        }
    }

    private fun handleAction() {
        if (isPokemonAdded) viewModel.removePokemon(myPokemon) else viewModel.savePokemon(myPokemon)
        viewModel.getOneById(myPokemon.id)
    }
}