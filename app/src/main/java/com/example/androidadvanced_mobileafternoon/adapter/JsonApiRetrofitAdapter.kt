package com.example.androidadvanced_mobileafternoon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvanced_mobileafternoon.databinding.ListJsonApiBinding

class JsonApiRetrofitAdapter(private val listReview:ArrayList<String>) : RecyclerView.Adapter<JsonApiRetrofitAdapter.ViewHolder> () {

    class ViewHolder(val Binding: ListJsonApiBinding) : RecyclerView.ViewHolder(Binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListJsonApiBinding.inflate(LayoutInflater.from(parent.context), parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listReview[position]){
                Binding.tvIsiJsonApi.text = listReview[position]
            }
        }
    }

    override fun getItemCount(): Int = listReview.size

}