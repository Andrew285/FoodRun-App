package com.rainyday.foodrun.feature.home.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home_root"

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    onRestaurantClick: (String) -> Unit
) {
    composable(HOME_ROUTE) {
        HomeScreen(onRestaurantClick = onRestaurantClick)
    }
}