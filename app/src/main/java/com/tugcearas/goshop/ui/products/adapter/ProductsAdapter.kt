package com.tugcearas.goshop.ui.products.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tugcearas.goshop.data.model.ProductResponseModel
import com.tugcearas.goshop.databinding.ProductsItemBinding
import com.tugcearas.goshop.util.diffutil.DiffUtilCallback

class ProductsAdapter(private var productOnClick:(ProductResponseModel) -> Unit)
    : ListAdapter<ProductResponseModel, ProductsAdapter.ProductViewHolder>(
    DiffUtilCallback<ProductResponseModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder (
        ProductsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: ProductsAdapter.ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ProductViewHolder(private var binding: ProductsItemBinding):RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(product:ProductResponseModel){
           binding.apply {
               productsItemTitle.text = product.title
               productsItemPrice.text = "$" + product.price.toString()
               Glide.with(itemView).load(product.image).into(binding.productsItemImageView)
           }
            binding.root.setOnClickListener {
                productOnClick(product)
            }
        }
    }
}