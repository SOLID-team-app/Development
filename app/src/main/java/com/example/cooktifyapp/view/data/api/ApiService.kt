package com.example.cooktifyapp.view.data.api

import com.example.cooktifyapp.view.data.Recipe.ListResep

import retrofit2.http.GET

interface ApiService {

    @GET("user/Recipe")
    suspend fun recipes(
    ): ListResep
}