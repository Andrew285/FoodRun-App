package com.rainyday.foodrun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rainyday.foodrun.feature.auth.presentation.navigation.LOGIN_ROUTE
import com.rainyday.foodrun.feature.auth.presentation.navigation.authNavGraph
import com.rainyday.foodrun.feature.home.presentation.HOME_ROUTE
import com.rainyday.foodrun.feature.home.presentation.homeNavGraph
import com.rainyday.foodrun.ui.theme.FoodRunTheme
import dagger.hilt.android.AndroidEntryPoint

const val SPLASH_ROUTE = "splash"


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRunTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
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
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}