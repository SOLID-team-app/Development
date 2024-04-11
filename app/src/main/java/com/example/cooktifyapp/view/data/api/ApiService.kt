package com.example.cooktifyapp.view.data.api

import com.example.cooktifyapp.view.data.Recipe.ListResep
import com.example.cooktifyapp.view.data.Recipe.ResponseFavorite
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipes

import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("user/Recipe")
    suspend fun recipes(
    ): ResponseRecipes

    @POST("Favorite")
    suspend fun favorite(
    ) : ResponseFavorite
}