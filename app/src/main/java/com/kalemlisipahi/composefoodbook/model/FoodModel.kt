package com.kalemlisipahi.composefoodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

@Entity
data class FoodModel(

    @PrimaryKey(autoGenerate = true)
    var uuid : Int? = null,

    @SerializedName("isim")
    var name : String,

    @SerializedName("kalori")
    var calorie : String,

    @SerializedName("karbonhidrat")
    var carbohydrate : String,

    var protein : String,

    @SerializedName("yag")
    var fat : String,

    @SerializedName("gorsel")
    var image : String

) {
    constructor() : this(null,"","","","","","")
}
