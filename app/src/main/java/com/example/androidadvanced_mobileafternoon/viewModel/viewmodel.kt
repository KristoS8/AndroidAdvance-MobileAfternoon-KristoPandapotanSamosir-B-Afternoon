package com.example.androidadvanced_mobileafternoon.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidadvanced_mobileafternoon.entity.noteEntity
import com.example.androidadvanced_mobileafternoon.repositories.repositories
import kotlinx.coroutines.launch
// selanjutnya buat viewnya/activity

class viewmodel(val repo: repositories) : ViewModel() {
    fun getAllData() = repo.getAllData()

    fun insertData(data: noteEntity) = viewModelScope.launch{
        repo.insertData(data)
    }
    
    fun updateData(data: noteEntity) = viewModelScope.launch {
        repo.updateData(data)
    }

    fun deleteData(id : noteEntity) = viewModelScope.launch {
        repo.deleteData(id)
    }

    fun getDataId(id: Int) = repo.getById(id)

}