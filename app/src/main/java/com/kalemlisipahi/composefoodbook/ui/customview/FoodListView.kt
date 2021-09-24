package com.kalemlisipahi.composefoodbook.ui.customview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kalemlisipahi.composefoodbook.model.FoodModel
import com.kalemlisipahi.composefoodbook.ui.customview.listitem.FoodListRow
import com.kalemlisipahi.composefoodbook.viewmodel.MainScreenViewModel

@Composable
fun FoodListView(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val foodList by remember { viewModel.foodList }
    val errorMessage by remember { viewModel.errorMessage }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(5.dp)) {
        items(foodList) { food ->
            FoodListRow(navController = navController, foodListItem = food)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading)
            CircularProgressIndicator(color = MaterialTheme.colors.primary)

        if (errorMessage.isNotEmpty())
            RetryView(errorMessage = errorMessage) {
                viewModel.getFoodList()
            }
    }
}