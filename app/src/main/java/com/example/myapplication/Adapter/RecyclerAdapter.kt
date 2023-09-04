package com.example.cleartripint.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Jokes
import com.example.myapplication.databinding.ItemLayoutBinding

class RecyclerAdapter(
    private var productList: List<Jokes>,

    ) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Jokes) = binding.apply {

            textTitle.text = joke.joke

        }
    }


    override fun getItemCount(): Int {
        return productList.size
    }


}