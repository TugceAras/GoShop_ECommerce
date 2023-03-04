package com.tugcearas.goshop.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.goshop.data.model.CartModel
import com.tugcearas.goshop.data.repository.ProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repo: ProductsRepo): ViewModel() {

    val getProduct: MutableLiveData<List<CartModel>> = MutableLiveData()
    val getAmount: MutableLiveData<Double> = MutableLiveData(0.0)

    init {
        getProductItem()
    }

    private fun getProductItem() = viewModelScope.launch(Dispatchers.IO) {
        val products = repo.getProductToCart()

        // when a new product is added
        products.forEach {
            it.count = 1
        }
        getProduct.postValue(products)

        val totalAmount = products.sumOf {
            (it.price ?: 0.0) * it.count
        }
        getAmount.postValue(totalAmount)
    }

    fun deleteProductFromCart(id:Int) = viewModelScope.launch(Dispatchers.IO) {
        repo.deleteProductFromCart(id)
        getProductItem()
    }

    fun increasePrice(price:Double){
        val currentAmount = getAmount.value ?: 0.0
        val newAmount = currentAmount + price
        getAmount.postValue(newAmount)
    }

    fun decreasePrice(price:Double){
        val currentAmount = getAmount.value ?: 0.0
        val newAmount = currentAmount - price
        getAmount.postValue(newAmount)
    }
}