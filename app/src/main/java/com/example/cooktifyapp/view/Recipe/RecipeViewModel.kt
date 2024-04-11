package com.example.cooktifyapp.view.Recipe

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipes
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipesItem
import com.example.cooktifyapp.view.data.repository.Repository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RecipeViewModel(private val mRepository: Repository):ViewModel() {

//    private var _recipes = MutableLiveData<List<ResponseRecipesItem?>?> ()
//    var recipe : LiveData<List<ResponseRecipesItem?>?> = _recipes
//
//
//    fun getRecipe(context: Context){
//        viewModelScope.launch {
//            try {
//                val successResponse = mRepository.getRecipes()
//                _recipes.value = successResponse.responseRecipes
//            }catch (e: HttpException){
//                val error = e.response()?.errorBody()?.string()
//                val errorMessage = Gson().fromJson(error, ResponseRecipes::class.java)
//                Toast.makeText(context,errorMessage.toString(), Toast.LENGTH_SHORT).show()
//
//            }
//
//        }
//    }

}