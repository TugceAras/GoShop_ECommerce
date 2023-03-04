package com.tugcearas.goshop.ui.payment.success

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.goshop.data.repository.ProductsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(private val repo: ProductsRepo): ViewModel() {

    fun clearCart() = viewModelScope.launch(Dispatchers.IO) {
        repo.clearCart()
    }
}