package com.tiffinbooking.ui.screen.user_login

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.tiffinbooking.R
import com.tiffinbooking.ui.localdatabase.TiffinDatabase
import com.tiffinbooking.ui.extension.checkNull
import com.tiffinbooking.ui.extension.emailValidation
import com.tiffinbooking.ui.extension.showMessage
import com.tiffinbooking.ui.theme.TiffinBookingTheme
import com.tiffinbooking.ui.theme.gray
import com.tiffinbooking.ui.theme.orange
import com.tiffinbooking.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserLoginScreen(navController: NavController,preference:TiffinDatabase) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var userEmailAddress by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(false) }
    var loggedIn by remember { mutableStateOf(false) }
    fun validation():Boolean {
        return if (!checkNull(userEmailAddress.toString().trim())) {
            context.showMessage("Please enter user email address.")
            false
        }else if (emailValidation(userEmailAddress.trim())) {
            context.showMessage("Please enter valid user email address.")
            false
        }else if (!checkNull(userPassword.toString().trim())) {
            context.showMessage("Please enter user password.")
            false
        }else {
            true
        }
    }
    TiffinBookingTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(orange)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.tiffin),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        Modifier,
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Card(
                            modifier = Modifier,
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(5.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "Login to continue",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black, fontSize = 16.sp)
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "Email",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black)
                                )

                                OutlinedTextField(
                                    value = userEmailAddress,
                                    shape = RoundedCornerShape(10.dp),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Email,
                                        capitalization = KeyboardCapitalization.Sentences
                                    ),
                                    maxLines = 1,
                                    singleLine = true,
                                    keyboardActions = KeyboardActions(
                                        onDone = { keyboardController?.hide() }),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Black, fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier
                                        .padding(vertical = 4.dp, horizontal = 0.dp)
                                        .fillMaxWidth()
                                        .focusable(false)
                                        .clickable(onClick = {

                                        }),
                                    placeholder = {
                                        Text(
                                            "Enter user email",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    onValueChange = {
                                        userEmailAddress = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "Password",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black)
                                )

                                OutlinedTextField(
                                    value = userPassword,
                                    shape = RoundedCornerShape(10.dp),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Password,
                                        capitalization = KeyboardCapitalization.Sentences
                                    ),
                                    maxLines = 1,
                                    singleLine = true,
                                    keyboardActions = KeyboardActions(
                                        onDone = { keyboardController?.hide() }),
                                    textStyle = TextStyle(
                                        fontSize = 16.sp,
                                        color = Color.Black, fontWeight = FontWeight.Medium
                                    ),
                                    modifier = Modifier
                                        .padding(vertical = 4.dp, horizontal = 0.dp)
                                        .fillMaxWidth()
                                        .focusable(false)
                                        .clickable(onClick = {

                                        }),
                                    placeholder = {
                                        Text(
                                            "Enter user password",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    visualTransformation = PasswordVisualTransformation(),
                                    onValueChange = {
                                        userPassword = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))
                                Button(
                                    modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White),
                                    contentPadding = PaddingValues(),
                                    shape = RoundedCornerShape(30.dp),
                                    onClick = {
                                        if(validation()) {
                                            loggedIn = true

                                        }
                                    },
                                ) {
                                    Box(
                                        modifier = Modifier.background(orange).then(Modifier.padding(vertical = 5.dp).fillMaxWidth()),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(
                                            text = "Login",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            modifier = Modifier.padding(10.dp))
                                    }
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "Don't have an account?",
                                        textAlign = TextAlign.End,
                                        style = TextStyle(color = gray)
                                    )

                                    Text(
                                        " Register", modifier = Modifier.clickable {
                                            navController.navigate("UserRegister")
                                        }, textAlign = TextAlign.End,
                                        style = TextStyle(color = orange, fontWeight = FontWeight.Bold)
                                    )
                                }
                            }
                        }
                    }

                }

            }
            if (showProgress) {
                Dialog(
                    onDismissRequest = { },
                    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .background(white, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(color = orange)
                    }
                }
            }
            if (loggedIn) {
                AlertDialog(
                    onDismissRequest = {
                        loggedIn = false
                    },
                    title = { Text(stringResource(id = R.string.app_name)) },
                    text = { Text("You have logged in successfully.") },
                    confirmButton = {
                        Button(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(),
                            shape = RoundedCornerShape(30.dp),
                            onClick = {
                                preference.isLogin = true
                                navController.navigate("TiffinListing") {
                                    popUpTo("UserLogin") {
                                        inclusive = true
                                    }
                                }
                                loggedIn = false
                            },
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(orange)
                                    .then(
                                        Modifier
                                            .padding(vertical = 5.dp)
                                            .fillMaxWidth()
                                    ),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = "Ok",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    },
                    dismissButton = {}
                )
            }

        }
    }
}