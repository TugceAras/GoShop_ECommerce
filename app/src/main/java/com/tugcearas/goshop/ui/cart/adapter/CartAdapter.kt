package com.tugcearas.goshop.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.goshop.data.model.CartModel
import com.tugcearas.goshop.databinding.CartItemBinding
import com.tugcearas.goshop.util.diffutil.DiffUtilCallback
import com.tugcearas.goshop.util.extensions.click

class CartAdapter(
    var onDeleteClick: (Int)->Unit = {},
    var onIncrease: (Double)->Unit = {},
    var onDecrease: (Double)->Unit = {}
) : ListAdapter<CartModel, CartAdapter.CartViewHolder>(
    DiffUtilCallback<CartModel>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CartViewHolder (
         CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class CartViewHolder(val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: CartModel){
            var productCount = 1

            binding.product = product
            binding.executePendingBindings()

            binding.cartItemDeleteButton.click {
                onDeleteClick(product.id)
            }

            binding.cartItemPlusButton.click {
                product.price?.let {
                    onIncrease(it)
                }
                productCount++
                binding.product!!.count = productCount
                binding.product = product // update the view
            }

            binding.cartItemMinusButton.click {
                  if(productCount!=1){
                      product.price?.let {
                          onDecrease(it)
                      }
                      productCount--
                      binding.product!!.count = productCount
                      binding.product = product
                  }
            }
        }
    }
}