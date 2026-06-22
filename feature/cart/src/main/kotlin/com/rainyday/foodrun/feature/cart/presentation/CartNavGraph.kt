package com.rainyday.foodrun.feature.cart.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val CART_ROUTE = "cart_route"

fun NavGraphBuilder.cartNavGraph(
    onCheckout: (restaurantId: String) -> Unit
) {
    composable(CART_ROUTE) {
        CartScreen(onCheckout = onCheckout)
    }
}