package com.example.cooktifyapp.view.Recipe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooktifyapp.R
import com.example.cooktifyapp.databinding.ActivityRecipeBinding
import com.example.cooktifyapp.view.adapter.RecipeAdapter
import com.example.cooktifyapp.view.data.Recipe.ListMakanan
import com.example.cooktifyapp.view.data.repository.ViewmodelFactory

class Recipe : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var adapter: RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeViewModel = obtainViewModel(this@Recipe)
        val LayoutManager = LinearLayoutManager(this)
        binding.rvRecipes.layoutManager = LayoutManager
        val itemDecoration = DividerItemDecoration(this, LayoutManager.orientation)
        binding.rvRecipes.addItemDecoration(itemDecoration)
        adapter = RecipeAdapter()
        binding.rvRecipes.adapter = adapter

        getRecipe()

    }

    private fun getRecipe() {
        recipeViewModel.recipe.observe(this) { recipeList ->
            adapter.submitList(recipeList)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): RecipeViewModel {
        val factory = ViewmodelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(RecipeViewModel::class.java)
    }
}