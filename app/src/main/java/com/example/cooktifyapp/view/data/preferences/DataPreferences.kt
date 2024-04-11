package com.example.cooktifyapp.view.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences


class DataStorePreference private constructor(private val dataStore: DataStore<Preferences>){
    companion object{
        @Volatile
        private var INSTANCE: DataStorePreference? = null
        fun getInstance(dataStore: DataStore<Preferences>):DataStorePreference{
            return INSTANCE ?: synchronized(this) {
                val instance = DataStorePreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}