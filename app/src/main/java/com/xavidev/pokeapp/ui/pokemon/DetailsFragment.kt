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
        val colorId = getColorByType(myPokemon.type)
        val color = ContextCompat.getColor(requireContext(), colorId)

        binding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            pokemon = myPokemon
            background = color
        }

        setupListeners()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dialog?.setCancelable(false)
        val factory = DetailsViewModel.DetailsViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    private fun setupListeners() {
        binding.toolbar.setNavigationOnClickListener { dismiss() }

        binding.imgAdd.setOnClickListener { viewModel.savePokemon(myPokemon) }
    }

    private fun getColorByType(name: String): Int {

        val colors = mapOf(
            R.color.normal_color to "normal",
            R.color.fire_color to "fire",
            R.color.water_color to "water",
            R.color.electric_color to "electric",
            R.color.grass_color to "grass",
            R.color.ice_color to "ice",
            R.color.fighting_color to "fighting",
            R.color.poison_color to "poison",
            R.color.ground_color to "ground",
            R.color.flying_color to "flying",
            R.color.psychic_color to "psychic",
            R.color.bug_color to "bug",
            R.color.rock_color to "rock",
            R.color.ghost_color to "ghost",
            R.color.dragon_color to "dragon",
            R.color.dark_color to "dark",
            R.color.steel_color to "steel",
            R.color.fairy_color to "fairy",
        )

        return colors.filterValues { value -> value == name }.keys.elementAt(0)
    }
}