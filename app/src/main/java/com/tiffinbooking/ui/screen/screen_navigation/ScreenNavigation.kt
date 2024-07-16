package com.tiffinbooking.ui.screen.screen_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tiffinbooking.ui.extension.checkNull
import com.tiffinbooking.ui.localdatabase.TiffinDatabase
import com.tiffinbooking.ui.screen.booking.BookingScreen
import com.tiffinbooking.ui.screen.tiffin_detail.TiffinDetailScreen
import com.tiffinbooking.ui.screen.tiffin_list.TiffinListing
import com.tiffinbooking.ui.screen.user_login.UserLoginScreen
import com.tiffinbooking.ui.screen.user_register.UserRegisterScreen
import com.tiffinbooking.ui.screen.user_splash.SplashScreen

@Composable
fun ScreenNavigation() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val tiffinDatabase = TiffinDatabase(context)
    NavHost(
        navController = navController,
        startDestination = "UserSplash"
    ) {
        composable(route = "UserSplash") {
            SplashScreen(navController = navController,tiffinDatabase)
        }
        composable(route = "UserLogin") {
            UserLoginScreen(navController = navController,tiffinDatabase)
        }
        composable(route = "UserRegister") {
            UserRegisterScreen(navController = navController,tiffinDatabase)
        }
        composable(route = "TiffinListing") {
            TiffinListing(navController = navController,tiffinDatabase)
        }
        composable(route = "Booking") {
            BookingScreen(navController = navController)
        }
        composable(route = "TiffinDetail"+"/{image}"+"/{name}"+"/{price}"+"/{detail}") {
            val name = it.arguments?.getString("name").toString()
            val image = it.arguments?.getString("image").toString().toInt()
            val price = it.arguments?.getString("price").toString()
            val detail = it.arguments?.getString("detail").toString()
            if (checkNull(name) && checkNull(price) && checkNull(detail)) {
                TiffinDetailScreen(
                    navController = navController,
                    name = name,
                    image = image,
                    price = price,
                    detail = detail
                )
            }
        }
    }

}