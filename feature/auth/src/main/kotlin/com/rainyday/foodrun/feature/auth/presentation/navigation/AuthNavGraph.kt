package com.rainyday.foodrun.feature.auth.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.rainyday.foodrun.feature.auth.presentation.LoginScreen
import com.rainyday.foodrun.feature.auth.presentation.RegisterScreen

const val AUTH_GRAPH_ROUTE = "auth"
const val LOGIN_ROUTE = "login"
const val REGISTER_ROUTE = "register"

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    onAuthSuccess: () -> Unit
) {
    composable(LOGIN_ROUTE) {
        LoginScreen(
            onNavigateToRegister = { navController.navigate(REGISTER_ROUTE) } ,
            onLoginSuccess = onAuthSuccess
        )
    }

    composable(REGISTER_ROUTE) {
        RegisterScreen(
            onNavigateToLogin = { navController.navigate(LOGIN_ROUTE) },
            onRegisterSuccess = onAuthSuccess,
        )
    }
}