package com.tugcearas.goshop.data.repository

import com.tugcearas.goshop.data.model.CartModel
import com.tugcearas.goshop.data.model.FavoriteModel
import com.tugcearas.goshop.data.model.ProductResponseModel
import com.tugcearas.goshop.data.source.local.ProductDao
import com.tugcearas.goshop.data.source.remote.GoShopApi
import com.tugcearas.goshop.util.resource.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductsRepo @Inject constructor(private val api:GoShopApi,private val db: ProductDao) {

    suspend fun getSpecificCategory(categoryName: String): Resource<List<ProductResponseModel>> {

        val favTitleList = withContext(Dispatchers.IO) { db.getFavoritesTitles().orEmpty() }
        return try {
            val getCategories = api.getSpecificCategory(categoryName).map {
                with(it) {
                    ProductResponseModel(
                        category,
                        description,
                        id,
                        image,
                        price,
                        title,
                        count,
                        favTitleList.contains(title)
                    )
                }
            }
            Resource.Success(getCategories)
        } catch (e: Exception) {
            Resource.Error(e.message.orEmpty())
        }
    }

    suspend fun addProductToCart(product: CartModel){
        db.addProductToCart(product)
    }

    fun getProductToCart():List<CartModel>{
        return db.getProductToCart()
    }

    suspend fun deleteProductFromCart(id:Int){
        db.deleteProductFromCart(id)
    }

    suspend fun addProductToFavorite(product: FavoriteModel) {
        db.addProductToFavorite(product)
    }

    suspend fun deleteProductFromFavorite(favId:Int){
        db.deleteProductFromFavorite(favId)
    }

    fun getProductToFavorite():List<FavoriteModel> {
        return db.getProductToFavorite()
    }

    suspend fun getAllCategory()= try {
        Resource.Success(api.getAllCategories())
    } catch (e: Exception) {
        Resource.Error(e.message.orEmpty())
    }

    suspend fun clearCart(){
        db.clearCart()
    }
}