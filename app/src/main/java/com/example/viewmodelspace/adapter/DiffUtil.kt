package com.example.viewmodelspace.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.viewmodelspace.model.Food

class DiffUtil : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem === newItem
    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean = oldItem == newItem
}