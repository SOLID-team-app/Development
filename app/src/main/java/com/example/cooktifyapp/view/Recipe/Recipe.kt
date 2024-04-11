package com.example.cooktifyapp.view.Recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cooktifyapp.databinding.ActivityRecipeBinding
import com.example.cooktifyapp.view.adapter.RecipeAdapter

class Recipe : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding
    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvRecipes.addItemDecoration(itemDecoration)

        setupRecyclerView()
        observeRecipes()

    }

    private fun setupRecyclerView() {
        adapter = RecipeAdapter()
        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@Recipe.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeRecipes() {
        recipeViewModel.recipes.observe(this) { recipes ->
            recipes?.let {
                adapter.submitList(it)
            }
        }
    }
}