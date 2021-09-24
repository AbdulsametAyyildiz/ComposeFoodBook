package com.kalemlisipahi.composefoodbook.ui.customview.listitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kalemlisipahi.composefoodbook.model.FoodModel

@Composable
fun FoodListRow(
    navController: NavController,
    foodListItem: FoodModel
) {

    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail_screen/${foodListItem.uuid}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Image(painter = rememberImagePainter(
            data = foodListItem.image),
            contentDescription = "Food Image",
            Modifier
                .size(100.dp, 100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.padding(20.dp))

        Column(){
            Text(
                text = foodListItem.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(2.dp)
            )

            Text(
                text = foodListItem.calorie,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(2.dp)
            )
        }
    }

}