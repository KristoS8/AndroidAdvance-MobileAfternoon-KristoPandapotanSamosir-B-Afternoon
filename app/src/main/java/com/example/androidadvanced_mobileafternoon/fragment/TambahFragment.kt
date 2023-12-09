package com.example.androidadvanced_mobileafternoon.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvanced_mobileafternoon.activity.DetailDataMhsActivity
import com.example.androidadvanced_mobileafternoon.activity.TambahDataActivity
import com.example.androidadvanced_mobileafternoon.activity.UpdateDataMhsActivity
import com.example.androidadvanced_mobileafternoon.adapter.adapterDataMhs
import com.example.androidadvanced_mobileafternoon.adapter.database
import com.example.androidadvanced_mobileafternoon.databinding.FragmentTambahBinding
import com.example.androidadvanced_mobileafternoon.entity.noteEntity
import com.example.androidadvanced_mobileafternoon.repositories.repositories
import com.example.androidadvanced_mobileafternoon.utils.viewModelFactory
import com.example.androidadvanced_mobileafternoon.viewModel.viewmodel


class TambahFragment : Fragment() {

    private lateinit var binding: FragmentTambahBinding
    private lateinit var adapter: adapterDataMhs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTambahBinding.inflate(inflater, container, false)

        init(noteViewModel())

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init(vm: viewmodel) {
        changeData(vm) { list ->
            val layoutManager = LinearLayoutManager(requireActivity())
            binding.rvDataMahasiswa.layoutManager = layoutManager
            adapter = adapterDataMhs(list, onDelete = { item ->
                vm.deleteData(item).let {
                    Toast.makeText(activity, "Data Telah dihapus", Toast.LENGTH_SHORT).show()
                }
            }, onClick = {
                val intent = Intent(activity, DetailDataMhsActivity::class.java)
                intent.putExtra("ID_NOTE", it.toString())
                startActivity(intent)
            }) {
                val intent = Intent(activity, UpdateDataMhsActivity::class.java)
                intent.putExtra("ID_NOTE", it.toString())
                startActivity(intent)
            }.also { adapter = it }

            adapter.notifyDataSetChanged()
            binding.rvDataMahasiswa.adapter = adapter
        }
        onclick()
    }

    private fun onclick() {
        binding.btnAddData.setOnClickListener {
            val intent = Intent(activity, TambahDataActivity::class.java)
            startActivity(intent)
        }
    }

    private fun changeData(vm: viewmodel, getdata: (List<noteEntity>) -> Unit) {
        vm.getAllData().observe(requireActivity()) { list ->
            if (list.isNotEmpty()) {
                getdata.invoke(list)
                binding.rvDataMahasiswa.visibility = View.VISIBLE
                binding.btnAddData.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.GONE
            } else {
                binding.rvDataMahasiswa.visibility = View.GONE
                binding.btnAddData.visibility = View.VISIBLE
                binding.tvEmpty.visibility = View.VISIBLE
            }
        }
    }

    private fun noteViewModel(): viewmodel {
        val database = database(requireActivity())
        val repo = repositories(database)
        val factory = viewModelFactory(repo)
        return ViewModelProvider(this, factory)[viewmodel::class.java]
    }

}