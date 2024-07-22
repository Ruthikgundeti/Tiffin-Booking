package com.tiffinbooking.ui.screen.support

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
fun SupportScreen(navController: NavController) {
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
                            text = "Support", color = Color.White,
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
                    Text(
                        text = "For more info and support, contact us!",
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp)
                    )
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                        Text(
                            text = "Ruthik1818@gmail.com", color = orange,
                            modifier = Modifier
                                .fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_local_phone_24),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(25.dp)
                                .height(25.dp)
                        )
                        Text(
                            text = "+44 7423 238277", color = orange,
                            modifier = Modifier
                                .fillMaxWidth().padding(start = 10.dp, end = 10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    }
                }
            }
        }


    }
}
