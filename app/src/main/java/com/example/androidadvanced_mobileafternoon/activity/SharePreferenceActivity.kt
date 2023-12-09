package com.example.androidadvanced_mobileafternoon.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidadvanced_mobileafternoon.databinding.ActivitySharePreferenceBinding
import com.example.androidadvanced_mobileafternoon.utils.CustomSharePreference

private lateinit var binding: ActivitySharePreferenceBinding
private lateinit var pref : CustomSharePreference

class SharePreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharePreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        loginClick()
        checkCondition()

    }

    private fun init() {
        pref = CustomSharePreference(this@SharePreferenceActivity)
    }

    private fun loginClick() {
        binding.backsharePref.setOnClickListener {
            pref.saveLogin(0).let {
                val i = Intent(this@SharePreferenceActivity,MainActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }

    private fun checkCondition() {
        if (pref.getLogin().equals(0)) {
            val intent = Intent(this@SharePreferenceActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }}