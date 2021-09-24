package com.kalemlisipahi.composefoodbook.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kalemlisipahi.composefoodbook.model.FoodModel
import com.kalemlisipahi.composefoodbook.service.database.dao.FoodDao

@Database(
    entities = [FoodModel::class],
    version = 1
)
abstract class FoodDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao
}