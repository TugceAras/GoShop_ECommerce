package com.tugcearas.goshop.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tugcearas.goshop.data.model.CartModel
import com.tugcearas.goshop.data.model.FavoriteModel

@Database(entities = [CartModel::class,FavoriteModel::class], version = 4,exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun getProductFromDao():ProductDao
}