package com.kalemlisipahi.composefoodbook.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kalemlisipahi.composefoodbook.ui.customview.RetryView
import com.kalemlisipahi.composefoodbook.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val foodItem by remember { viewModel.foodItem }

    viewModel.getFoodItem(id)

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberImagePainter(data = foodItem.image),
                contentDescription = "Food Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                alignment = Alignment.TopCenter
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = foodItem.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primaryVariant,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = foodItem.calorie,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = foodItem.carbohydrate,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = foodItem.fat,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Text(
                text = foodItem.protein,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}