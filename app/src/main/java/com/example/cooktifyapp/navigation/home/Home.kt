package com.example.cooktifyapp.navigation.home

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

        val navView : BottomNavigationView = binding.bottomBar

        val navControler = findNavController(R.id.fragmentDashboard)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navHome, R.id.navCamera, R.id.navFavorite, R.id.navProfile
        ).build()

        setupActionBarWithNavController(navControler, appBarConfiguration)
        navView.setupWithNavController(navControler)


    }
}