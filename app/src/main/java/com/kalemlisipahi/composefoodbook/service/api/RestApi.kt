package com.kalemlisipahi.composefoodbook.service.api

import com.kalemlisipahi.composefoodbook.model.FoodModel
import retrofit2.http.GET

interface RestApi {

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    suspend fun getAllData(): List<FoodModel>
}