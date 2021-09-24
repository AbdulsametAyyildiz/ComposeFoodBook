package com.kalemlisipahi.composefoodbook.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kalemlisipahi.composefoodbook.ui.customview.FoodListView
import com.kalemlisipahi.composefoodbook.ui.customview.SearchBar
import com.kalemlisipahi.composefoodbook.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            //Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ){
                viewModel.searchFoodList(it)
            }

            FoodListView(navController = navController)
        }

    }

}