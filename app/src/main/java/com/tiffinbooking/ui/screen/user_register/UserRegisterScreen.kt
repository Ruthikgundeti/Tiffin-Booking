package com.tiffinbooking.ui.screen.user_register

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
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
fun UserRegisterScreen(navController: NavController,preference:TiffinDatabase) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()
    val cityScroll = rememberScrollState()

    var isShowCity by rememberSaveable { mutableStateOf(false) }
    val cityList =
        listOf(
            "London",
            "Birmingham",
            "Manchester",
            "Liverpool",
            "Leeds",
            "Sheffield",
            "Teesside",
            "Bristo"
        )

    val icon = if (isShowCity)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val choiceList = arrayListOf<String>().apply {
        clear()
        add("Veg")
        add("Non-Veg")
    }
    var choice by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var userEmailAddress by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var userConfirmPassword by remember { mutableStateOf("") }
    var showProgress by remember { mutableStateOf(false) }

    fun validation():Boolean {
        return if (!checkNull(firstName.toString().trim())) {
            context.showMessage("Please enter first name.")
            false
        }else if (!checkNull(lastName.toString().trim())) {
            context.showMessage("Please enter last name.")
            false
        }else if (!checkNull(city.toString().trim())) {
            context.showMessage("Please select city.")
            false
        }else if (!checkNull(userEmailAddress.toString().trim())) {
            context.showMessage("Please enter user email address.")
            false
        }else if (emailValidation(userEmailAddress.trim())) {
            context.showMessage("Please enter valid user email address.")
            false
        }else if (!checkNull(choice.toString().trim())) {
            context.showMessage("Please select meal.")
            false
        }else if (!checkNull(userPassword.toString().trim())) {
            context.showMessage("Please enter user password.")
            false
        }else if (!checkNull(userConfirmPassword.toString().trim())) {
            context.showMessage("Please enter user confirm password.")
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
                    .verticalScroll(scrollState)
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
                Spacer(modifier = Modifier.height(20.dp))
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
                            colors = CardDefaults.cardColors(containerColor = white),
                            elevation = CardDefaults.cardElevation(5.dp),
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "Register to continue",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black, fontSize = 16.sp)
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "First Name",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black)
                                )

                                OutlinedTextField(
                                    value = firstName,
                                    shape = RoundedCornerShape(10.dp),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
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
                                            "Enter first name",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    onValueChange = {
                                        firstName = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "Last Name",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black)
                                )

                                OutlinedTextField(
                                    value = lastName,
                                    shape = RoundedCornerShape(10.dp),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
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
                                            "Enter last name",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    onValueChange = {
                                        lastName = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))

                                Text(
                                    "City",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black)
                                )
                                OutlinedTextField(
                                    value = if (city != "") city else "Select city",
                                    onValueChange = { city = it },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { isShowCity = !isShowCity },
                                    enabled = false,
                                    trailingIcon = {
                                        Icon(
                                            icon, "contentDescription", tint = Color.Black
                                        )
                                    },
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        focusedBorderColor = gray,
                                        unfocusedBorderColor = gray,
                                        disabledBorderColor = gray
                                    ),
                                    textStyle = TextStyle(color = Color.Black),
                                    shape = RoundedCornerShape(10.dp),
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    if (isShowCity) {
                                        Popup(
                                            alignment = Alignment.TopCenter,
                                            properties = PopupProperties(
                                                excludeFromSystemGesture = true,
                                            ),
                                            onDismissRequest = { isShowCity = false }
                                        ) {

                                            Column(
                                                modifier = Modifier
                                                    .heightIn(max = 220.dp)
                                                    .verticalScroll(state = cityScroll)
                                                    .padding(10.dp)
                                                    .border(width = 1.dp, color = Color.Gray),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                            ) {

                                                cityList.onEachIndexed { index, item ->
                                                    if (index != 0) {
                                                        Divider(
                                                            thickness = 1.dp,
                                                            color = Color.LightGray
                                                        )
                                                    }
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .background(white)
                                                            .padding(10.dp)
                                                            .clickable {
                                                                city = item
                                                                isShowCity = !isShowCity
                                                            },
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Text(
                                                            text = item,
                                                            style = TextStyle(color = Color.Black)
                                                        )
                                                    }
                                                }

                                            }
                                        }
                                    }
                                }
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
                                            "Enter email",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    onValueChange = {
                                        userEmailAddress = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = "Choice of meal : ", color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    style = TextStyle(color = Color.Black)
                                )
                                Column(
                                    modifier = Modifier.padding(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    choiceList.forEach { name ->
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            RadioButton(
                                                selected = (name == choice),
                                                onClick = { choice = name },
                                                colors = RadioButtonDefaults.colors(selectedColor = Color.Black)

                                            )
                                            Text(
                                                text = name,
                                                style = TextStyle(color = Color.Black),
                                                color = Color.Black,
                                                modifier = Modifier
                                                    .padding(start = 8.dp)
                                                    .clickable {
                                                        choice = name
                                                    }
                                            )
                                        }
                                    }

                                }
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
                                            "Enter password",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    visualTransformation = PasswordVisualTransformation(),
                                    onValueChange = {
                                        userPassword = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    "Confirm Password",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(color = Color.Black)
                                )

                                OutlinedTextField(
                                    value = userConfirmPassword,
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
                                            "Enter confirm password",
                                            color = gray,
                                            fontSize = 16.sp
                                        )
                                    },
                                    visualTransformation = PasswordVisualTransformation(),
                                    onValueChange = {
                                        userConfirmPassword = it
                                    })
                                Spacer(modifier = Modifier.height(20.dp))
                                Button(
                                    modifier = Modifier.padding(vertical = 5.dp).fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = white),
                                    contentPadding = PaddingValues(),
                                    shape = RoundedCornerShape(30.dp),
                                    onClick = {
                                        if(validation()) {
                                            preference.isLogin = true
                                            navController.navigate("TiffinListing") {
                                                popUpTo("UserRegister") {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    },
                                ) {
                                    Box(
                                        modifier = Modifier.background(orange).then(Modifier.padding(vertical = 5.dp).fillMaxWidth()),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(
                                            text = "Register",
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
                                        "Already have an account?",
                                        textAlign = TextAlign.End,
                                        style = TextStyle(color = gray)
                                    )

                                    Text(
                                        " Login", modifier = Modifier.clickable {
                                            navController.navigateUp()
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
                            .background(Color.Black, shape = RoundedCornerShape(8.dp))
                    ) {
                        CircularProgressIndicator(color = orange)
                    }
                }
            }

        }
    }
}