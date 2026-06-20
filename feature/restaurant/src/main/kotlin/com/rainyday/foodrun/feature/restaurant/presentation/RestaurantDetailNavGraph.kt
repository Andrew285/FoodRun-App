package com.rainyday.foodrun.feature.restaurant.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val RESTAURANT_DETAIL = "restaurant/{restaurantId}"

fun NavGraphBuilder.restaurantDetailNavGraph(
    navController: NavHostController,
    onAddToCart: (String, String, Double) -> Unit
) {
    composable(
        route = RESTAURANT_DETAIL,
        arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
    ) {
        RestaurantDetailScreen(
            onBack = { navController.popBackStack() },
            onAddToCart = { menuItem ->
                onAddToCart(menuItem.id, menuItem.name, menuItem.price)
            }
        )
    }
}

fun restaurantDetailRoute(restaurantId: String) = "restaurant/$restaurantId"