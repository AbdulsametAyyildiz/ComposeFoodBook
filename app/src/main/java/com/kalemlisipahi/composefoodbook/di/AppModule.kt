package com.kalemlisipahi.composefoodbook.di

import android.content.Context
import androidx.room.Room
import com.kalemlisipahi.composefoodbook.repository.FoodRepository
import com.kalemlisipahi.composefoodbook.service.api.RestApi
import com.kalemlisipahi.composefoodbook.service.database.FoodDatabase
import com.kalemlisipahi.composefoodbook.service.database.dao.FoodDao
import com.kalemlisipahi.composefoodbook.util.Constants.BASE_URL
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

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,FoodDatabase::class.java,"FoodBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectFoodDao(database: FoodDatabase) = database.foodDao()

    @Singleton
    @Provides
    fun provideFoodRepository(
        api: RestApi,
        foodDao: FoodDao
    ) = FoodRepository(foodDao,api)

    @Singleton
    @Provides
    fun provideRestApi(): RestApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RestApi::class.java)
    }
}