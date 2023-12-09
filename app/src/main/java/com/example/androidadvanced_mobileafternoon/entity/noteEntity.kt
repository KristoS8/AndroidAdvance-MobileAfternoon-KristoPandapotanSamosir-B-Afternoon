package com.example.androidadvanced_mobileafternoon.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table1")
data class noteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "nama")
    val nama: String = "",
    @ColumnInfo(name = "umur")
    val umur: String = "",
    @ColumnInfo(name = "Asal Kampus")
    val asalKampus: String = ""
)
