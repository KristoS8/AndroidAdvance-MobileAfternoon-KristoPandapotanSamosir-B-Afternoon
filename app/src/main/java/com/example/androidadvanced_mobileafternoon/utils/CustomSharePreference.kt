package com.example.androidadvanced_mobileafternoon.utils

import android.content.Context
import android.content.SharedPreferences

class CustomSharePreference(val context: Context) {

    private val prefs : SharedPreferences = context.getSharedPreferences("Simpan", Context.MODE_PRIVATE)

    fun saveLogin(login : Int){
        val editor = prefs.edit()
        editor.putInt("SaveLogin", login)
        editor.apply()
    }

    fun saveEmail(email:String){
        val editor = prefs.edit()
        editor.putString("SaveEmail", email)
        editor.apply()
    }

    fun  getLogin (): Int = prefs.getInt("SaveLogin",0)
}