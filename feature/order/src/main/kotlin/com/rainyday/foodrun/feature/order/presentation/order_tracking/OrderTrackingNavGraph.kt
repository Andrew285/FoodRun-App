package com.rainyday.foodrun.feature.order.presentation.order_tracking

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ORDER_TRACKING_ROUTE = "order_tracking/{orderId}"

fun NavGraphBuilder.orderTrackingNavGraph(
    navController: NavHostController,
    onBackToHome: () -> Unit
) {
    composable(
        route = ORDER_TRACKING_ROUTE,
        arguments = listOf(navArgument("orderId") { type = NavType.StringType })
    ) {
        OrderTrackingScreen(onBackToHome = onBackToHome)
    }
}

fun orderTrackingRoute(orderId: String) = "order_tracking/$orderId"