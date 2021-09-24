package com.kalemlisipahi.composefoodbook.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalemlisipahi.composefoodbook.model.FoodModel
import com.kalemlisipahi.composefoodbook.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val foodRepository: FoodRepository,
): ViewModel() {

    val foodItem = mutableStateOf(FoodModel())

    fun getFoodItem(id: Int){
        viewModelScope.launch(context = Dispatchers.Main) {
            foodItem.value = foodRepository.getFoodItem(id)
        }
    }

}