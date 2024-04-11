package com.example.cooktifyapp.view.data.api

import com.example.cooktifyapp.view.data.Recipe.Recipes
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipe
import retrofit2.http.GET

interface ApiService {

    @GET("user/Recipe")
    suspend fun getRecipes(
    ): Recipes
}