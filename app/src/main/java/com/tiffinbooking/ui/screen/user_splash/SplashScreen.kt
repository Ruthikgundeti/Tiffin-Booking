package com.tiffinbooking.ui.screen.user_splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.tiffinbooking.R
import com.tiffinbooking.ui.localdatabase.TiffinDatabase
import com.tiffinbooking.ui.theme.TiffinBookingTheme
import com.tiffinbooking.ui.theme.white
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(navController: NavController,preference:TiffinDatabase) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        delay(3.seconds)
        if(preference.isLogin) {
            navController.navigate("TiffinListing") {
                popUpTo("UserSplash") {
                    inclusive = true
                }
            }
        }else{
            navController.navigate("UserLogin") {
                popUpTo("UserSplash") {
                    inclusive = true
                }
            }
        }


    }
    TiffinBookingTheme {
        Scaffold {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.tiffin),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(white)
                )
            }
        }
    }
}