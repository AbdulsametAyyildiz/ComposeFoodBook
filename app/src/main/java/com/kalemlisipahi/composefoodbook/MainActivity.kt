package com.kalemlisipahi.composefoodbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kalemlisipahi.composefoodbook.ui.theme.ComposeFoodBookTheme
import com.kalemlisipahi.composefoodbook.ui.view.DetailScreen
import com.kalemlisipahi.composefoodbook.ui.view.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFoodBookTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main_screen" ) {
                    composable("main_screen"){
                        MainScreen(navController = navController)
                    }

                    composable("detail_screen/{dataId}", arguments = listOf(
                        navArgument("dataId"){
                            type = NavType.IntType
                        }
                    )) {
                        val dataId = remember {
                            it.arguments.let {  it!!.getInt("dataId") }
                        }

                        DetailScreen(navController = navController, id = dataId)
                    }
                }
            }
        }
    }
}