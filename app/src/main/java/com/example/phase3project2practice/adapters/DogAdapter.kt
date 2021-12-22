package com.example.phase3project2practice.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.phase3project2practice.database.DogImage
import com.example.phase3project2practice.databinding.DogsBinding


//Not yet Implemented
class DogAdapter() : ListAdapter<DogImage, DogAdapter.DogViewHolder>(DogDiffCallback()) {

    inner class DogViewHolder(val binding: DogsBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DogsBinding.inflate(layoutInflater, parent, false)
        return DogViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        holder.binding.apply {
            dogImage.load(dog.dogImageURL)
            Log.i("onBind", "position $position --- ${dog.dogImageURL}")
        }

    }
}