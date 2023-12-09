package com.example.androidadvanced_mobileafternoon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvanced_mobileafternoon.entity.noteEntity
import com.example.androidadvanced_mobileafternoon.databinding.ListDataMahasiswaBinding

class adapterDataMhs(private val list: List<noteEntity>, val onDelete: (noteEntity) -> Unit, val onClick:(Int) -> Unit, val onClickUpdate : (Int) -> Unit)
    : RecyclerView.Adapter<adapterDataMhs.viewHolder>() {

    class viewHolder(val Binding: ListDataMahasiswaBinding): RecyclerView.ViewHolder(Binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = ListDataMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        with(holder){
            with(list[position]){
                Binding.tvNamaMhs.text = this.nama
                Binding.tvUmurMhs.text = this.umur
                Binding.tvAsalKampus.text = this.asalKampus

                Binding.ivDelete.setOnClickListener{
                    onDelete.invoke(this)
                }

                Binding.ivUpdate.setOnClickListener{
                    onClickUpdate.invoke(this.id)
                }

                Binding.rvCardMhs.setOnClickListener{
                    onClick.invoke(this.id)
                }

            }
        }
    }
}