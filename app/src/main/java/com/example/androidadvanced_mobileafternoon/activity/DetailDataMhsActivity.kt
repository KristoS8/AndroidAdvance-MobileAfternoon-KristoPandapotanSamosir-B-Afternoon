package com.example.androidadvanced_mobileafternoon.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.adapter.database
import com.example.androidadvanced_mobileafternoon.databinding.ActivityDetailDataMhsBinding
import com.example.androidadvanced_mobileafternoon.repositories.repositories
import com.example.androidadvanced_mobileafternoon.utils.viewModelFactory
import com.example.androidadvanced_mobileafternoon.viewModel.viewmodel

private lateinit var binding: ActivityDetailDataMhsBinding

class DetailDataMhsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDataMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID_NOTE")
        getData(noteViewModel(),getId!!.toInt())

    }

    private fun getData(viewModel: viewmodel, getId: Int) {
        viewModel.getDataId(getId).observe(this){
            binding.tvNamaDetail.text = it.nama
            binding.tvUmurDetail.text = it.umur
            binding.tvAsalDetail.text = it.asalKampus
        }
    }

    private fun noteViewModel(): viewmodel {
        val database = database(this)
        val repo = repositories(database)
        val factory = viewModelFactory(repo)
        return ViewModelProvider(this,factory)[viewmodel::class.java]
    }
}