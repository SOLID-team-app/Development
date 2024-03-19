package com.example.cooktifyapp.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityOnBoardingBinding

class OnBoarding : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}