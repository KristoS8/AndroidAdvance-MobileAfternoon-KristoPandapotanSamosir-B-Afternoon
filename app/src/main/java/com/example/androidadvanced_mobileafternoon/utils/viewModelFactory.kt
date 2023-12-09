package com.example.androidadvanced_mobileafternoon.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidadvanced_mobileafternoon.repositories.repositories

class viewModelFactory(private val repositories: repositories): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(repositories::class.java)
            return constructor.newInstance(repositories)
        }catch (e: Exception){

        }
        return super.create(modelClass)
    }
}