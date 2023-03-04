package com.tugcearas.goshop.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.goshop.data.model.FavoriteModel
import com.tugcearas.goshop.databinding.FavoriteItemBinding
import com.tugcearas.goshop.util.diffutil.DiffUtilCallback
import com.tugcearas.goshop.util.extensions.click

class FavoriteAdapter(
    var onDeleteClick: (Int)->Unit={}
) : ListAdapter<FavoriteModel, FavoriteAdapter.FavoriteViewHolder>(
    DiffUtilCallback<FavoriteModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= FavoriteViewHolder (
        FavoriteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
       holder.bind(currentList[position])
    }

    inner class FavoriteViewHolder(val binding: FavoriteItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product:FavoriteModel){
            binding.product = product
            binding.executePendingBindings()

            binding.favDeleteButton.click {
                onDeleteClick(product.id)
            }
        }
    }
}