package com.rainyday.foodrun.feature.order.presentation.checkout

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val CHECKOUT_ROUTE = "checkout/{restaurantId}"

fun NavGraphBuilder.checkoutNavGraph(
    navController: NavHostController,
    onOrderPlaced: (orderId: String) -> Unit
) {
    composable(
        route = CHECKOUT_ROUTE,
        arguments = listOf(navArgument("restaurantId") { type = NavType.StringType })
    ) { backStackEntry ->
        val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""
        CheckoutScreen(
            restaurantId = restaurantId,
            onBack = { navController.popBackStack() },
            onOrderPlaced = onOrderPlaced
        )
    }
}

fun checkoutRoute(restaurantId: String) = "checkout/$restaurantId"