package com.example.cooktifyapp.view.data.repository

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cooktifyapp.view.Recipe.RecipeViewModel
import com.example.cooktifyapp.view.data.Di.DataInjection

class ViewmodelFactory private constructor(private val mAppRepository: Repository) : ViewModelProvider.NewInstanceFactory() {



    companion object{
        fun getInstance(context: Context): ViewmodelFactory =  ViewmodelFactory(DataInjection.ProviderRepository(context))
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
//            return RecipeViewModel(mAppRepository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}