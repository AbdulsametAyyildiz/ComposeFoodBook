package com.kalemlisipahi.composefoodbook.service.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kalemlisipahi.composefoodbook.model.FoodModel

@Dao
interface FoodDao {

    @Insert
    suspend fun insertFoods(vararg foodModel: FoodModel)

    @Query("SELECT * FROM FoodModel")
    suspend fun observeFoods(): List<FoodModel>

    @Query("SELECT * FROM FoodModel WHERE uuid = :uuid")
    suspend fun getFood(uuid: Int) : FoodModel

    @Query("DELETE FROM FoodModel")
    suspend fun deleteAll();

}