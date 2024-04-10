package com.example.cooktifyapp.view.Recipe

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipe
import com.example.cooktifyapp.view.data.repository.Repository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RecipeViewModel : ViewModel() {
    private val recipeLiveData: MutableLiveData<List<Recipe>> = MutableLiveData()
    private val recipeRepository: Repository = Repository(getRecipes())

    // Method to fetch recipes from repository using Retrofit
    fun fetchRecipes() {
        recipeRepository.getRecipes().enqueue(object : Callback<List<Recipe>> {
            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                if (response.isSuccessful) {
                    val recipes: List<Recipe>? = response.body()
                    recipeLiveData.value = recipes
                } else {
                    // Handle unsuccessful response
                    // You can log an error message or show an error to the user
                }
            }

            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                // Handle network errors
                // You can log an error message or show an error to the user
            }
        })
    }

    // Method to observe LiveData from UI
    fun getRecipes(): LiveData<List<Recipe>> {
        return recipeLiveData
    }
}