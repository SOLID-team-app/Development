package com.example.cooktifyapp.view.Recipe

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityRecipeBinding
import com.example.cooktifyapp.view.adapter.RecipeAdapter
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipes
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem
import com.example.cooktifyapp.view.data.repository.ViewmodelFactory
import com.example.cooktifyapp.view.settings.SettingsActivity

class Recipe : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gotosettings.setOnClickListener {
            val intent = Intent(this@Recipe, SettingsActivity::class.java)
            startActivity(intent)
        }

        recipeViewModel = obtainViewModel(this@Recipe)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvRecipes.addItemDecoration(itemDecoration)
        adapter = RecipeAdapter()
        binding.rvRecipes.adapter = adapter
        recipeViewModel.recipe.observe(this){
            showRecipe(it)
        }

    }

     private fun showRecipe(recipeList: List<ResponseRecipesItem?>?) {
            adapter.submitList(recipeList)
    }

    private fun obtainViewModel(activity: AppCompatActivity): RecipeViewModel {
        val factory = ViewmodelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RecipeViewModel::class.java)
    }
}