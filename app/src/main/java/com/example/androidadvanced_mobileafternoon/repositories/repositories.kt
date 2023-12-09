package com.example.androidadvanced_mobileafternoon.repositories

import androidx.lifecycle.LiveData
import com.example.androidadvanced_mobileafternoon.adapter.database
import com.example.androidadvanced_mobileafternoon.entity.noteEntity

class repositories(val database: database) {
    // mengkonvert data dari dao ke bentuk fun, karena dao itu masih dalam bentuk query/data objek
    fun getAllData():LiveData<List<noteEntity>> = database.dao().getAll()
    // live data dieksekusi di main thread
    fun getById(id: Int):LiveData<noteEntity> = database.dao().getById(id)
    // suspend fun adalan fun yang dieksekusi di background thread
    suspend fun insertData(data: noteEntity) = database.dao().insertData(data)

    suspend fun updateData(data: noteEntity) = database.dao().updateData(data)

    suspend fun deleteData(id: noteEntity) = database.dao().deleteData(id)
}