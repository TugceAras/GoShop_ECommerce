package com.tugcearas.goshop.ui.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.goshop.data.model.ProductResponseModel
import com.tugcearas.goshop.data.repository.ProductsRepo
import com.tugcearas.goshop.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsRepo: ProductsRepo): ViewModel() {

    val getCategory: MutableLiveData<Resource<List<ProductResponseModel>>> = MutableLiveData()

    fun getSpecificCategory(categoryName:String) = viewModelScope.launch{
        getCategory.postValue(handleResponse(productsRepo.getSpecificCategory(categoryName)))
    }

    private fun handleResponse(response: Resource<List<ProductResponseModel>>) =
        when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
}