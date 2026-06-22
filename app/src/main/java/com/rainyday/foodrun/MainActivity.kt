package com.rainyday.foodrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rainyday.foodrun.core.network.AuthEventBus
import com.rainyday.foodrun.core.network.AuthState
import com.rainyday.foodrun.feature.auth.presentation.navigation.LOGIN_ROUTE
import com.rainyday.foodrun.feature.auth.presentation.navigation.authNavGraph
import com.rainyday.foodrun.core.domain.model.CartItemDomain
import com.rainyday.foodrun.feature.cart.presentation.CART_ROUTE
import com.rainyday.foodrun.feature.cart.presentation.CartViewModel
import com.rainyday.foodrun.feature.cart.presentation.cartNavGraph
import com.rainyday.foodrun.feature.home.presentation.HOME_ROUTE
import com.rainyday.foodrun.feature.home.presentation.homeNavGraph
import com.rainyday.foodrun.feature.order.presentation.checkout.checkoutNavGraph
import com.rainyday.foodrun.feature.order.presentation.checkout.checkoutRoute
import com.rainyday.foodrun.feature.order.presentation.order_tracking.orderTrackingNavGraph
import com.rainyday.foodrun.feature.order.presentation.order_tracking.orderTrackingRoute
import com.rainyday.foodrun.feature.restaurant.presentation.restaurantDetailNavGraph
import com.rainyday.foodrun.feature.restaurant.presentation.restaurantDetailRoute
import com.rainyday.foodrun.ui.theme.FoodRunTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val SPLASH_ROUTE = "splash"


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authEventBus: AuthEventBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRunTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val currentBackStack by navController.currentBackStackEntryAsState()
                    val currentRoute = currentBackStack?.destination?.route

                    val bottomBarRoutes = setOf(HOME_ROUTE, CART_ROUTE)
                    val showBottomBar = currentRoute in bottomBarRoutes

                    LaunchedEffect(Unit) {
                        authEventBus.events.collect { event ->
                            if (event == AuthState.Unauthorized) {
                                navController.navigate(LOGIN_ROUTE) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        }
                    }
                    val cartViewModel: CartViewModel = hiltViewModel()

                    Scaffold(
                        bottomBar = {
                            if (showBottomBar) {
                                NavigationBar {
                                    NavigationBarItem(
                                        selected = currentRoute == HOME_ROUTE,
                                        onClick = {
                                            navController.navigate(HOME_ROUTE) {
                                                popUpTo(HOME_ROUTE) { inclusive = false }
                                            }
                                        },
                                        icon = { Icon(Icons.Default.Home, contentDescription = "Головна") },
                                        label = { Text("Головна") }
                                    )
                                    NavigationBarItem(
                                        selected = currentRoute == CART_ROUTE,
                                        onClick = {
                                            navController.navigate(CART_ROUTE) {
                                                popUpTo(HOME_ROUTE) { inclusive = false }
                                                launchSingleTop = true
                                            }
                                        },
                                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Кошик") },
                                        label = { Text("Кошик") }
                                    )
                                }
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = SPLASH_ROUTE,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable(SPLASH_ROUTE) {
                                SplashScreen(
                                    onAuthenticated = {
                                        navController.navigate(HOME_ROUTE) {
                                            popUpTo(SPLASH_ROUTE) { inclusive = true }
                                        }
                                    },
                                    onUnauthenticated = {
                                        navController.navigate(LOGIN_ROUTE) {
                                            popUpTo(SPLASH_ROUTE) { inclusive = true }
                                        }
                                    },
                                )
                            }
                            authNavGraph(
                                navController = navController,
                                onAuthSuccess = {
                                    navController.navigate(HOME_ROUTE) {
                                        popUpTo(LOGIN_ROUTE) { inclusive = true }
                                    }
                                }
                            )
                            homeNavGraph(
                                navController = navController,
                                onRestaurantClick = { restaurantId ->
                                    navController.navigate(restaurantDetailRoute(restaurantId))
                                }
                            )
                            restaurantDetailNavGraph(
                                navController = navController,
                                onAddToCart = { menuItem, restaurantId ->
                                    cartViewModel.addItem(
                                        CartItemDomain(
                                            menuItemId = menuItem.id,
                                            restaurantId = restaurantId,
                                            name = menuItem.name,
                                            price = menuItem.price,
                                            quantity = 1,
                                            imageUrl = menuItem.imageUrl
                                        )
                                    )
                                }
                            )
                            cartNavGraph(
                                onCheckout = { restaurantId ->
                                    navController.navigate(checkoutRoute(restaurantId)) {
                                        popUpTo(CART_ROUTE) { inclusive = false }
                                    }
                                }
                            )
                            checkoutNavGraph(
                                navController = navController,
                                onOrderPlaced = { orderId ->
                                    navController.navigate(orderTrackingRoute(orderId)) {
                                        popUpTo(CART_ROUTE) { inclusive = true }
                                    }
                                }
                            )
                            orderTrackingNavGraph(
                                navController = navController,
                                onBackToHome = {
                                    navController.navigate(HOME_ROUTE) {
                                        popUpTo(0) { inclusive = true }
                                    }
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}