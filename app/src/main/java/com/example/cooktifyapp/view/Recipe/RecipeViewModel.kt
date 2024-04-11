package com.example.cooktifyapp.view.Recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem
import com.example.cooktifyapp.view.data.api.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeViewModel():ViewModel() {

    private val _recipes = MutableLiveData<List<ResponseRecipesItem>>()
    val recipes: LiveData<List<ResponseRecipesItem>> = _recipes

    init {
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
                    _recipes.value = response.body()
                } else {
                    Log.e("RecipeViewModel", "Failed to fetch recipes: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseRecipesItem>>, t: Throwable) {
                Log.e("RecipeViewModel", "Failed to fetch recipes: ${t.message}")
            }
        })
    }

}