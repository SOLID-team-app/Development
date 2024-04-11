package com.example.cooktifyapp.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.cooktifyapp.databinding.ActivityDetailBinding
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
        val bahan = intent.getParcelableExtra<ResponseRecipesItem>("bahan_makanan")
        if (bahan != null) {
            Glide.with(applicationContext)
                .load(bahan.linkGambar)
                .into(binding.ivImage)
            binding.tvBahanUtama.text = bahan.bahanUtama
        } else {
            // Tangani kasus di mana objek yang diterima null
            // Misalnya, tampilkan pesan kesalahan atau lakukan tindakan yang sesuai
        }
    }


}