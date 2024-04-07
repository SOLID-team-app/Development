package com.example.cooktifyapp.view.navigation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomBar

        val navController = findNavController(R.id.fragmentDashboard) // Sesuaikan dengan ID fragment yang sesuai dengan container navigasi.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navHome, R.id.navCamera, R.id.navFavorite, R.id.navProfile // Pastikan ID tujuan dalam AppBarConfiguration sesuai dengan yang ada di XML navigation Anda.
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

}