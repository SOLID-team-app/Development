package com.example.cooktifyapp.view.data.repository


import com.example.cooktifyapp.view.data.api.ApiService

class Repository(private val apiService: ApiService, ) {
    suspend fun getRecipes() = apiService.recipes()

    suspend fun getFavorite(bahan : Int) = apiService.favorite(bahan)
}
