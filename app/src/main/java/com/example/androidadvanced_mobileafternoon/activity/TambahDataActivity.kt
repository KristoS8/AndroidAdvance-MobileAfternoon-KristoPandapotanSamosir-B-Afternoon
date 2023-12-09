package com.example.androidadvanced_mobileafternoon.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.adapter.database
import com.example.androidadvanced_mobileafternoon.databinding.ActivityTambahDataBinding
import com.example.androidadvanced_mobileafternoon.databinding.FragmentTambahBinding
import com.example.androidadvanced_mobileafternoon.databinding.ListDataMahasiswaBinding
import com.example.androidadvanced_mobileafternoon.entity.noteEntity
import com.example.androidadvanced_mobileafternoon.repositories.repositories
import com.example.androidadvanced_mobileafternoon.utils.viewModelFactory
import com.example.androidadvanced_mobileafternoon.viewModel.viewmodel

private lateinit var binding: ActivityTambahDataBinding

class TambahDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitAdd.setOnClickListener{
            addData(noteViewModel())
        }
    }

    private fun addData(vm: viewmodel) {
        vm.insertData(
            noteEntity(nama = binding.etNamaAdd.text.toString(), umur = binding.etUmurAdd.text.toString(), asalKampus = binding.etAsalKampusAdd.text.toString())
        ).let {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("key", 1)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun noteViewModel(): viewmodel {
        val database = database(this)
        val repo = repositories(database)
        val factory = viewModelFactory(repo)
        return ViewModelProvider(this,factory)[viewmodel::class.java]
    }
}