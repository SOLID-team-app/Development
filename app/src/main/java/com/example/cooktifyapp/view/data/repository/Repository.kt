package com.example.cooktifyapp.view.data.repository

import com.example.cooktifyapp.view.data.api.ApiService

class Repository(private val apiService: ApiService, ) {

        suspend fun Recipe() = apiService.getRecipes()
}