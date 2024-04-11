package com.example.cooktifyapp.view.Recipe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityRecipeBinding
import com.example.cooktifyapp.view.adapter.RecipeAdapter
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem
import com.example.cooktifyapp.view.data.api.ApiConfig
import com.example.cooktifyapp.view.data.repository.ViewmodelFactory
import com.example.cooktifyapp.view.settings.SettingsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Recipe : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.gotosetting.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvRecipes.addItemDecoration(itemDecoration)

        getRecipes()

    }

    private fun getRecipes() {
        val client = ApiConfig.getApiservice().recipes()
        client.enqueue(object : Callback<List<ResponseRecipesItem>> {
            override fun onResponse(
                call: Call<List<ResponseRecipesItem>>,
                response: Response<List<ResponseRecipesItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setDataRecipe(responseBody)
                    } else {
                        Log.e("Recipe", "Response body is null")
                    }
                } else {
                    Log.e("Recipe", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseRecipesItem>>, t: Throwable) {
                Log.e("Recipe", "onFailure: ${t.message}")
            }
        })
    }


    private fun setDataRecipe(users: List<ResponseRecipesItem>) {
        val userAdapter = RecipeAdapter()
        userAdapter.submitList(users)
        binding.rvRecipes.adapter = userAdapter
    }
}