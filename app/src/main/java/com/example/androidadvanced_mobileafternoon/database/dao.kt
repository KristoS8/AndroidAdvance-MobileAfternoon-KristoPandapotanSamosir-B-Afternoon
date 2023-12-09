package com.example.androidadvanced_mobileafternoon.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.androidadvanced_mobileafternoon.entity.noteEntity

@Dao
interface dao {
    // query database, setelah itu buat databasenya/database note
    @Query("SELECT * FROM table1")
    fun getAll():LiveData<List<noteEntity>>
    @Query("SELECT * FROM table1 WHERE id = :id")
    fun getById(id:Int):LiveData<noteEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:noteEntity)
    @Update()
    suspend fun updateData(data: noteEntity)
    @Delete()
    suspend fun deleteData(data:noteEntity)

}