package com.rainyday.foodrun.feature.restaurant.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rainyday.foodrun.core.domain.model.MenuItemDomain

const val RESTAURANT_DETAIL = "restaurant/{restaurantId}"

fun NavGraphBuilder.restaurantDetailNavGraph(
    navController: NavHostController,
    onAddToCart: (MenuItemDomain, String) -> Unit
) {
    composable(
        route = RESTAURANT_DETAIL,
        arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
    ) { backStackEntry ->
        val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
        RestaurantDetailScreen(
            onBack = { navController.popBackStack() },
            onAddToCart = { menuItem -> onAddToCart(menuItem, restaurantId) }
        )
    }
}

fun restaurantDetailRoute(restaurantId: String) = "restaurant/$restaurantId"