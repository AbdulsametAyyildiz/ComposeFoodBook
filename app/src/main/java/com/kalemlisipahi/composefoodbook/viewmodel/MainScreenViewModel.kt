package com.kalemlisipahi.composefoodbook.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalemlisipahi.composefoodbook.model.FoodModel
import com.kalemlisipahi.composefoodbook.repository.FoodRepository
import com.kalemlisipahi.composefoodbook.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    val foodList = mutableStateOf<List<FoodModel>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialFoodList = listOf<FoodModel>()
    private var isSearchStarting = true

    init {
        getFoodList()
    }

    fun getFoodList() {
        //if(isOnline(context)){
            isLoading.value = true
            viewModelScope.launch(context = Dispatchers.IO) {
                val result = repository.getFoodList()
                withContext(context = Dispatchers.Main) {
                    when(result) {
                        is Resource.Success -> {
                            foodList.value += repository.getLocalList()
                            errorMessage.value = ""
                            isLoading.value = false
                        }

                        is Resource.Error -> {
                            foodList.value += repository.getLocalList()
                            errorMessage.value = result.message!!
                            isLoading.value = false
                        }
                        else -> {}
                    }
                }
            }
        /*}
        else{
            viewModelScope.launch(context = Dispatchers.IO) {
                repository.getLocalList()
            }
        }*/

    }

    fun searchFoodList(query: String) {
        val listToSearch = if(isSearchStarting) {
            foodList.value
        } else {
            initialFoodList
        }

        viewModelScope.launch(context = Dispatchers.Default) {
            if (query.isEmpty()){
                foodList.value = initialFoodList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(),ignoreCase = true)
            }

            if (isSearchStarting) {
                initialFoodList = foodList.value
                isSearchStarting = false
            }

            foodList.value = results
        }
    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        if (capabilities != null) {
            return true
            /*when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }*/
        }
        return false
    }

}