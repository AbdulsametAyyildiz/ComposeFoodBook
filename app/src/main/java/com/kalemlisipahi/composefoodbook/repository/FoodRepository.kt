package com.kalemlisipahi.composefoodbook.repository

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.lifecycle.LiveData
import com.kalemlisipahi.composefoodbook.model.FoodModel
import com.kalemlisipahi.composefoodbook.service.api.RestApi
import com.kalemlisipahi.composefoodbook.service.database.dao.FoodDao
import com.kalemlisipahi.composefoodbook.util.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class FoodRepository @Inject constructor(
    private val foodDao: FoodDao,
    private val api: RestApi
) {

    suspend fun getFoodList(): Resource<List<FoodModel>>{
        val response = try {
            api.getAllData()
        }catch (t: Throwable){
            return Resource.Error(t.message.toString())
        }

        foodDao.deleteAll()
        foodDao.insertFoods(*response.toTypedArray())
        return Resource.Success(response)
    }

    suspend fun getLocalList(): List<FoodModel>{
        return foodDao.observeFoods()
    }

    suspend fun getFoodItem(id: Int): FoodModel{
        return foodDao.getFood(id)
    }
}