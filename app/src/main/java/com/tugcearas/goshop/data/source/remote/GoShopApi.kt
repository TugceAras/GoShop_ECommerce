package com.tugcearas.goshop.data.source.remote

import com.tugcearas.goshop.data.model.ProductResponseModel
import com.tugcearas.goshop.util.constants.Constants
import com.tugcearas.goshop.util.resource.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GoShopApi {

    // get category names
    @GET(Constants.ALL_CATEGORIES)
    suspend fun getAllCategories(): List<String>

    @GET(Constants.SPECIFIC_CATEGORY)
    suspend fun getSpecificCategory(
        @Path("category") category:String
    ):List<ProductResponseModel>
}