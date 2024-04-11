package com.example.cooktifyapp.view.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityDetailBinding
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem

class Detail : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getData()
    }

    private fun getData() {
        val bahan = intent.getParcelableExtra<ResponseRecipesItem>("recipe")
        if (bahan != null) {
            Glide.with(applicationContext)
                .load(bahan.linkGambar)
                .placeholder(R.drawable.tulisan_cooktify)
                .into(binding.ivImage)
            binding.tvBahanUtama.text = bahan.bahanUtama
        }

    }
}