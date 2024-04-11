package com.example.cooktifyapp.view.Recipe

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipe
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem
import com.example.cooktifyapp.view.data.api.ApiConfig
import com.example.cooktifyapp.view.data.repository.Repository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class RecipeViewModel():ViewModel() {

//    companion object {
//        private const val TAG = "DetailViewModel"
//    }
//
//    private val userData = MutableLiveData<ResponseRecipesItem?>()
//
//    suspend fun fetchRecipesData() {
//        val client = ApiConfig.getApiservice().recipes()
//        client.enqueue(object : Callback<ResponseRecipesItem> {
//            override fun onResponse(
//                call: Call<ResponseRecipesItem>,
//                response: Response<ResponseRecipesItem>
//            ) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        userData.postValue(responseBody)
//                    }
//                } else {
//                    Log.e(TAG, "onResponse error: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseRecipesItem>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
//
//    // Metode untuk mendapatkan LiveData objek data yang akan ditampilkan di halaman DetailActivity
//    fun getRecipesData(): MutableLiveData<ResponseRecipesItem?> {
//        return userData
//    }

}