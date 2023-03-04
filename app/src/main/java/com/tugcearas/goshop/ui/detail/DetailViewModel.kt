package com.tugcearas.goshop.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.goshop.data.model.CartModel
import com.tugcearas.goshop.data.model.FavoriteModel
import com.tugcearas.goshop.data.model.ProductResponseModel
import com.tugcearas.goshop.data.repository.ProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productsRepo: ProductsRepo,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    private var _product:MutableLiveData<ProductResponseModel> = MutableLiveData()
    val product get() = _product

    private var _favState:MutableLiveData<Pair<Boolean,Boolean>> = MutableLiveData()
    val favState get() = _favState

    init {
        getProductModel()
    }

    private fun getProductModel(){
        savedStateHandle.get<ProductResponseModel>("product")?.let {
            _product.value = it
            _favState.value = Pair(it.isFavorite,false)
        }
    }

    fun addProductToCart() = viewModelScope.launch {
        _product.value?.let {
            productsRepo.addProductToCart(
                CartModel(it.id,it.image,it.title,it.price,it.count)
            )
        }
    }

    fun setFavoriteState() = viewModelScope.launch {
        _product.value?.let {
            if (_favState.value?.first == true){
                productsRepo.deleteProductFromFavorite(it.id)
            }
            else{
                productsRepo.addProductToFavorite(
                    FavoriteModel(it.id,it.image,it.title,it.price)
                )
            }
            _favState.value = Pair(!_favState.value?.first!!,true)
        }
    }
}