package com.example.cooktifyapp.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}