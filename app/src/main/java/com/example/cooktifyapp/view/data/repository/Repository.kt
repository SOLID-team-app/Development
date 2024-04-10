package com.example.cooktifyapp.view.data.repository


import com.example.cooktifyapp.view.data.Recipe.ListResep
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipe
import com.example.cooktifyapp.view.data.api.ApiService
import okhttp3.Call

class Repository {
    private val recipeService: ApiService = getRecipes().retrofit.create(ApiService::class.java)


    fun getRecipes(): Call<List<ListResep>> {
        return recipeService.getRecipes()
    }
}
