package com.example.cooktifyapp.view.data.Di

import android.content.Context
import com.example.cooktifyapp.view.data.api.ApiConfig

import com.example.cooktifyapp.view.data.repository.Repository

object DataInjection {
    fun ProviderRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiservice()
        return Repository(apiService)
    }
}