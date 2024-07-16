package com.tiffinbooking.ui.screen.booking

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tiffinbooking.R
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
fun BookingScreen(navController: NavController) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val nestedScroll = rememberScrollState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var booked by remember { mutableStateOf(false) }

    fun validation(): Boolean {
        return if (!checkNull(name.toString().trim())) {
            context.showMessage("Please enter name.")
            false
        } else if (!checkNull(email.toString().trim())) {
            context.showMessage("Please enter email address.")
            false
        } else if (emailValidation(email.trim())) {
            context.showMessage("Please enter valid user email address.")
            false
        }else if (!checkNull(phone.toString().trim())) {
            context.showMessage("Please enter phone number.")
            false
        } else if (phone.length!=10) {
            context.showMessage("Phone number should be 10 digit.")
            false
        } else if (!checkNull(address.toString().trim())) {
            context.showMessage("Please enter address.")
            false
        } else {
            true
        }
    }
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
                            text = "Tiffin Booking", color = Color.White,
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
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(nestedScroll)
                        .background(color = white)
                ) {
                    Text(
                        "Name",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = Color.Black)
                    )

                    OutlinedTextField(
                        value = name,
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
                                "Enter name",
                                color = gray,
                                fontSize = 16.sp
                            )
                        },
                        onValueChange = {
                            name = it
                        })
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Email",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = Color.Black)
                    )

                    OutlinedTextField(
                        value = email,
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
                            email = it
                        })
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Phone Number",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = Color.Black)
                    )

                    OutlinedTextField(
                        value = phone,
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
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
                                "Enter phone number",
                                color = gray,
                                fontSize = 16.sp
                            )
                        },
                        onValueChange = {
                            phone = it
                        })
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Address",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = Color.Black)
                    )

                    OutlinedTextField(
                        value = address,
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        maxLines = 3,
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
                                "Enter address",
                                color = gray,
                                fontSize = 16.sp
                            )
                        },
                        onValueChange = {
                            address = it
                        })
                    Spacer(modifier = Modifier.height(20.dp))
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
                            if (validation()) {
                                booked = true
                            }
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
                                text = "Submit",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(30.dp))

                }
            }
        }
        if (booked) {
            AlertDialog(
                onDismissRequest = {
                    booked = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Your tiffin has been booked successfully.") },
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
                            navController.navigateUp()
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
                                text = "OK",
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
