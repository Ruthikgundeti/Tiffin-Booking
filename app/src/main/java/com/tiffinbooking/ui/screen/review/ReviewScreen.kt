package com.tiffinbooking.ui.screen.review

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tiffinbooking.R
import com.tiffinbooking.ui.extension.DatePickerDialogBox
import com.tiffinbooking.ui.extension.checkNull
import com.tiffinbooking.ui.extension.emailValidation
import com.tiffinbooking.ui.extension.showMessage
import com.tiffinbooking.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReviewScreen(navController: NavController) {
    val context = LocalContext.current

    TiffinBookingTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = white)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "why RUTHIK GUNDETI Tiffin", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Medium, fontSize = 18.sp)
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
                        .fillMaxSize()
                        .padding(10.dp)
                        .background(color = white)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_earth),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(100.dp)
                                .height(80.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp,end = 10.dp)) {
                            Text(
                                text = "Ghar ka khana", color = green,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                            )
                            Text(
                                text = "Delicious Home Style food with minimal oil / masalas", color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_variety),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(100.dp)
                                .height(80.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Amazing variety", color = green,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                            )
                            Text(
                                text = "You will love the variety coz at SpiceBox - No dish is repeated All Month!!", color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
                            )
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_order),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(100.dp)
                                .height(80.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Easy Ordering", color = green,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                            )
                            Text(
                                text = "Ordering your meal on the SpiceBox! Website will take less than 2 mins!!", color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
                            )
                        }
                    }

                }
            }
        }


    }
}
