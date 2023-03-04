package com.tugcearas.goshop.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugcearas.goshop.data.repository.ProductsRepo
import com.tugcearas.goshop.util.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: ProductsRepo) : ViewModel() {

    val getCategoryList: MutableLiveData<Resource<List<String>>> = MutableLiveData()

    init {
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        getCategoryList.postValue(Resource.Loading())
        getCategoryList.postValue(handleProductResponse(repo.getAllCategory()))
    }

    private fun handleProductResponse(response: Resource<List<String>>) =
        when (response) {
            is Resource.Success -> Resource.Success(response.data.orEmpty())
            is Resource.Error -> Resource.Error(response.message.orEmpty())
            is Resource.Loading -> Resource.Loading()
        }
}