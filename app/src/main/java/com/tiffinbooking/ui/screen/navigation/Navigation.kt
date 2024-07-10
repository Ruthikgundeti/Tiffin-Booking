package com.tiffinbooking.ui.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tiffinbooking.ui.screen.login.LoginScreen
import com.tiffinbooking.ui.screen.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Splash"
    ) {
        composable(route = "Splash") {
            SplashScreen(navController = navController)
        }
        composable(route = "Login") {
            LoginScreen(navController = navController)
        }

    }

}