package com.tiffinbooking.ui.screen.screen_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tiffinbooking.ui.extension.checkNull
import com.tiffinbooking.ui.localdatabase.TiffinDatabase
import com.tiffinbooking.ui.screen.aboutUs.AboutUsScreen
import com.tiffinbooking.ui.screen.booking.BookingScreen
import com.tiffinbooking.ui.screen.location.LocationScreen
import com.tiffinbooking.ui.screen.mealPlans.MealPlansScreen
import com.tiffinbooking.ui.screen.myOrders.MyOrders
import com.tiffinbooking.ui.screen.review.ReviewScreen
import com.tiffinbooking.ui.screen.support.SupportScreen
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
        startDestination = "TiffinListing"
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
        composable(route = "AboutUsScreen") {
            AboutUsScreen(navController = navController)
        }
        composable(route = "ReviewScreen") {
            ReviewScreen(navController = navController)
        }
        composable(route = "LocationScreen") {
            LocationScreen(navController = navController)
        }
        composable(route = "SupportScreen") {
            SupportScreen(navController = navController)
        }
        composable(route = "MealPlansScreen") {
            MealPlansScreen(navController = navController)
        }
        composable(route = "MyOrders") {
            MyOrders(navController = navController)
        }
        composable(route = "Booking"+"/{image}"+"/{name}"+"/{standard}"+"/{mini}"+"/{type}") {
            val image = it.arguments?.getString("image").toString().toInt()
            val name = it.arguments?.getString("name").toString()
            val standard = it.arguments?.getString("standard").toString()
            val mini = it.arguments?.getString("mini").toString()
            val type = it.arguments?.getString("type").toString()
            BookingScreen(navController = navController,image=image,name = name,standard = standard,mini = mini,type = type)
        }
        composable(route = "TiffinDetail"+"/{image}"+"/{name}"+"/{standard}"+"/{mini}"+"/{detail}"+"/{type}") {
            val name = it.arguments?.getString("name").toString()
            val image = it.arguments?.getString("image").toString().toInt()
            val standard = it.arguments?.getString("standard").toString()
            val mini = it.arguments?.getString("mini").toString()
            val detail = it.arguments?.getString("detail").toString()
            val type = it.arguments?.getString("type").toString()
            if (checkNull(name) && checkNull(standard) && checkNull(mini) && checkNull(detail)&& checkNull(type)) {
                TiffinDetailScreen(
                    navController = navController,
                    name = name,
                    image = image,
                    standard = standard,
                    mini = mini,
                    detail = detail,
                    type = type
                )
            }
        }
    }

}