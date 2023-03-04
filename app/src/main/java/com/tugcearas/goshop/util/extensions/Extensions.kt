package com.tugcearas.goshop.util.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.tugcearas.goshop.R

fun View.click(function: ()->Unit){
    this.setOnClickListener {
        function()
    }
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun Context.toastMessage(message:String) = Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

fun ImageView.downloadImage(imageUrl:String){
    Glide.with(context).load(imageUrl).into(this)
}

@BindingAdapter("android:downloadImage")
fun bindImage(imageView:ImageView, imageUrl:String){
    imageView.downloadImage(imageUrl)
}

@BindingAdapter("favState")
fun favState(imageView:ImageView, isFavorite:Boolean){
    if (isFavorite) imageView.setImageResource(R.drawable.fav_icon_fill)
    else imageView.setImageResource(R.drawable.fav_icon_outline)
}
