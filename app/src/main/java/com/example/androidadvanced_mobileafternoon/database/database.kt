package com.example.androidadvanced_mobileafternoon.adapter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidadvanced_mobileafternoon.database.dao
import com.example.androidadvanced_mobileafternoon.entity.noteEntity

@Database(entities = [noteEntity::class], version = 2)
abstract class database: RoomDatabase() {

    abstract fun dao(): dao

    companion object{
        private val dbName = "database.db"
        // volatile untuk mengecek di device udh ada terinstall databasenya
        @Volatile
        private var instance: database? = null
        private val LOCK = Any()

        // fun invoke yang membuat databasenya dapat dipanggil di activity
        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            database::class.java,
            dbName
        )
            // untuk keperluan migrasi otomatis sesuai dengan version
            .fallbackToDestructiveMigration().build()
    }
}