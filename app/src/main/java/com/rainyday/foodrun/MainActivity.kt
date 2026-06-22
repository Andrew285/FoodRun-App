package com.rainyday.foodrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rainyday.foodrun.core.network.AuthEventBus
import com.rainyday.foodrun.core.network.AuthState
import com.rainyday.foodrun.feature.auth.presentation.navigation.LOGIN_ROUTE
import com.rainyday.foodrun.feature.auth.presentation.navigation.authNavGraph
import com.rainyday.foodrun.feature.cart.domain.model.CartItemDomain
import com.rainyday.foodrun.feature.cart.presentation.CartViewModel
import com.rainyday.foodrun.feature.cart.presentation.cartNavGraph
import com.rainyday.foodrun.feature.home.presentation.HOME_ROUTE
import com.rainyday.foodrun.feature.home.presentation.homeNavGraph
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

                    NavHost(
                        navController = navController,
                        startDestination = SPLASH_ROUTE
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
                            navController = navController,
                            onCheckout = { /* TODO: feature:order */ }
                        )
                    }
                }
            }
        }
    }
}