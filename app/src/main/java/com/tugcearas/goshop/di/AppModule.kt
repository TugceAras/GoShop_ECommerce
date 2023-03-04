package com.tugcearas.goshop.di

import android.content.Context
import androidx.room.Room
import com.tugcearas.goshop.data.source.local.ProductDao
import com.tugcearas.goshop.data.source.local.ProductDatabase
import com.tugcearas.goshop.data.source.remote.GoShopApi
import com.tugcearas.goshop.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit:Retrofit):GoShopApi{
        return retrofit.create(GoShopApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context):ProductDatabase =
        Room.databaseBuilder(context,ProductDatabase::class.java,"productDatabase")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(database:ProductDatabase):ProductDao{
        return database.getProductFromDao()
    }
}