package com.tiffinbooking.ui.screen.myOrders

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tiffinbooking.ui.application.AppConfig.Companion.list
import com.tiffinbooking.ui.theme.TiffinBookingTheme
import com.tiffinbooking.ui.theme.orange
import com.tiffinbooking.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyOrders(navController: NavController) {
    val context = LocalContext.current
    val nestedScroll = rememberScrollState()

    TiffinBookingTheme {
        Column(
            modifier = Modifier
                .background(color = orange)
        ) {
            SmallTopAppBar(
                title = {
                    Text(
                        text = "My Orders", color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            tint = Color.White,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = orange,
                    titleContentColor = Color.White
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = white)
                    .verticalScroll(nestedScroll)
            ) {

                if (list.size > 0) {
                    Spacer(modifier = Modifier.height(20.dp))
                    list.forEachIndexed { index, model ->
                        Card(
                            modifier = Modifier
                                .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                                .background(color = white)
                                .fillMaxWidth()
                                .height(IntrinsicSize.Max),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                        ) {
                            Box(Modifier) {
                                Image(
                                    painter = painterResource(id = model.image),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                )
                            }
                            Text(
                                model.getProductName() ?: "",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductQuantity() ?: "",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductPrice() ?: "",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductMealTime(),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductMealType(),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductMealPlan(),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductDuration(),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                            Text(
                                model.getProductDate(),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(vertical = 5.dp, horizontal = 10.dp)
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No Orders Found.", color = Color.Black,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}

