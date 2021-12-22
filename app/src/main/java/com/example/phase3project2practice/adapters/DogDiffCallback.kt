package com.example.phase3project2practice.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.phase3project2practice.database.DogImage

class DogDiffCallback : DiffUtil.ItemCallback<DogImage>() {
    override fun areItemsTheSame(oldItem: DogImage, newItem: DogImage): Boolean {
        return oldItem.dogImageURL == newItem.dogImageURL
    }

    override fun areContentsTheSame(oldItem: DogImage, newItem: DogImage): Boolean {
        return oldItem == newItem
    }

}