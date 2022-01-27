package com.example.viewmodelspace.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodelspace.databinding.DefaultSingleLayBinding
import com.example.viewmodelspace.databinding.FavoriteSingleLayBinding
import com.example.viewmodelspace.model.Food



class RecyclerAdapter(val onClick:ItemClick) : ListAdapter<Food, RecyclerView.ViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if (viewType == NON_FAVORITE_ITEM) {
            return DefaultVH(
                DefaultSingleLayBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return FavoriteVH(
                FavoriteSingleLayBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isFavorite) {
            FAVORITE_ITEM
        } else {
            NON_FAVORITE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is DefaultVH) {
            holder.onBind(item,onClick)
        } else if (holder is FavoriteVH) {
            holder.onBind(item,onClick)
        }
    }

    class DefaultVH(private val binding: DefaultSingleLayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(food: Food,onClick: ItemClick) {
            with(binding) {
                foodNameTextview.text = food.name
                makeFavoriteButton.setOnClickListener {
                    onClick.onClick(adapterPosition)

                }

            }

        }
    }

    class FavoriteVH(private val binding: FavoriteSingleLayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(food: Food,onClick: ItemClick) {
            with(binding) {
                foodNameTextview.text = food.name
                dropFromFavoriteButton.setOnClickListener {
                    onClick.onClick(adapterPosition)

                }
            }
        }
    }

    fun ntf(position: Int){
        notifyItemChanged(position)
    }
    companion object {
        const val NON_FAVORITE_ITEM = 1
        const val FAVORITE_ITEM = 2
    }
}