package com.example.cooktifyapp.view.data.api

import com.example.cooktifyapp.view.data.RecipeResponse.ResponseFavorite
import com.example.cooktifyapp.view.data.RecipeResponse.ResponseRecipesItem
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("user/Recipe")
   fun recipes(
    ): Call<List<ResponseRecipesItem>>

    @POST("Favorite")
    suspend fun favorite(
    ) : ResponseFavorite
}