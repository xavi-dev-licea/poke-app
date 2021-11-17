package com.xavidev.pokeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.xavidev.pokeapp.R
import com.xavidev.pokeapp.databinding.ActivityMainBinding

class MainMenu : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()
    }

    private fun setupNavigation() {
        val bottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.fragmentContainer)

        bottomNavigationView.setupWithNavController(navController)
    }
}