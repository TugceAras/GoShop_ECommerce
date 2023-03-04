package com.tugcearas.goshop.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.goshop.R
import com.tugcearas.goshop.databinding.HomeCategoryItemBinding
import com.tugcearas.goshop.util.diffutil.DiffUtilCallback

class HomeAdapter(private var onCategoryClick: (String) -> Unit)
    : ListAdapter<String, HomeAdapter.HomeViewHolder>(
    DiffUtilCallback<String>(
        itemsTheSame = { oldItem, newItem ->
            oldItem == newItem
        },
        contentsTheSame = { oldItem, newItem ->
            oldItem == newItem
        }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        HomeCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) =
        holder.bind(currentList[position], categoryToDrawableMap[categories[position]] ?: 0)

    inner class HomeViewHolder(private var binding: HomeCategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: String, drawableResId: Int) {
            binding.categoryNameTextView.text = category
            if (drawableResId != 0) {
                binding.categoryImageView.setImageResource(drawableResId)
            }
            binding.root.setOnClickListener {
                onCategoryClick(category)
            }
        }
    }

    private val categories = listOf(
        "Electronics",
        "Jewelry",
        "Men's Clothes",
        "Women's Clothes"
    )

    private val categoryToDrawableMap = mapOf(
        "Electronics" to R.drawable.electronics,
        "Jewelry" to R.drawable.jewelry,
        "Men's Clothes" to R.drawable.mens_clothing,
        "Women's Clothes" to R.drawable.womans_clothing
    )
}