package com.example.cooktifyapp.view.data.api

import com.example.cooktifyapp.view.data.Recipe.ResponseFavorite
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipes

import retrofit2.Call
import retrofit2.http.Field

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {

    @GET("user/Recipe")
    suspend fun recipes(): ResponseRecipes

    @POST("Favorite")
    suspend fun favorite(
        @Field("bahan_utama") bahan: Int
    ) : ResponseFavorite
}