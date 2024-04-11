package com.example.cooktifyapp.view.Recipe

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooktifyapp.view.data.Recipe.Recipes
import com.example.cooktifyapp.view.data.Recipe.ResponseRecipe
import com.example.cooktifyapp.view.data.repository.Repository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RecipeViewModel(private val mRepository: Repository):ViewModel() {

    private var _recipes = MutableLiveData<List<ResponseRecipe>?>()
    var recipe : LiveData<List<ResponseRecipe>?> = _recipes


    fun getRecipe(context: Context){
        viewModelScope.launch {
            try {
                val successResponse = mRepository.Recipe()
                val message = successResponse.message
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                _recipes.value = successResponse.recipes
            }catch (e: HttpException){
                val error = e.response()?.errorBody()?.string()
                val errormessage = Gson().fromJson(error, Recipes::class.java)
                Toast.makeText(context,errormessage.message.toString(), Toast.LENGTH_SHORT).show()

            }

        }
    }

}