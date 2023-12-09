package com.example.androidadvanced_mobileafternoon.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidadvanced_mobileafternoon.R
import com.example.androidadvanced_mobileafternoon.adapter.database
import com.example.androidadvanced_mobileafternoon.databinding.ActivityUpdateDataMhsBinding
import com.example.androidadvanced_mobileafternoon.entity.noteEntity
import com.example.androidadvanced_mobileafternoon.repositories.repositories
import com.example.androidadvanced_mobileafternoon.utils.viewModelFactory
import com.example.androidadvanced_mobileafternoon.viewModel.viewmodel

private lateinit var binding: ActivityUpdateDataMhsBinding

class UpdateDataMhsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getId = intent.getStringExtra("ID_NOTE")
        getData(noteViewModel(),getId!!.toInt())

        binding.btnSubmitAdd.setOnClickListener{
            addData(noteViewModel(),getId!!.toInt())
        }

    }

    private fun addData(vm: viewmodel, id: Int) {
        vm.updateData(
            noteEntity(id = id, nama = binding.etNamaUpdate.text.toString(), umur = binding.etUmurUpdate.text.toString(),
                asalKampus = binding.etAsalKampusUpdate.text.toString())
        ).let {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("key", 1)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun getData(viewModel: viewmodel, getId: Int) {
        viewModel.getDataId(getId).observe(this){
            binding.etNamaUpdate.setText(it.nama)
            binding.etUmurUpdate.setText(it.umur)
            binding.etAsalKampusUpdate.setText(it.asalKampus)
        }
    }

    private fun noteViewModel(): viewmodel {
        val database = database(this)
        val repo = repositories(database)
        val factory = viewModelFactory(repo)
        return ViewModelProvider(this,factory)[viewmodel::class.java]
    }
}