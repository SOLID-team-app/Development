package com.example.cooktifyapp.view.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityDetailBinding
import com.example.cooktifyapp.databinding.ActivityRecipeBinding
import com.example.cooktifyapp.view.Recipe.RecipeViewModel
import com.example.cooktifyapp.view.adapter.RecipeAdapter
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem

class Detail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding



    companion object{
        const val ITEM_IMAGE_URL = "itemImageUrl"
        const val ITEM_BAHAN_UTAMA = "itemBahanUtama"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getDetail()

    }
    private fun getDetail(){
        val bahan = intent.getParcelableExtra<ResponseRecipesItem>("bahan_makanan") as ResponseRecipesItem
        Glide.with(applicationContext)
            .load(bahan.linkGambar)
            .into(binding.ivImage)
        binding.tvBahanUtama.text = bahan.bahanUtama
    }

}