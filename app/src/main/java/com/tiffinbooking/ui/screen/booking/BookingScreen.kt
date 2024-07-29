package com.tiffinbooking.ui.screen.booking

import android.annotation.SuppressLint
import android.util.Log
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
import com.tiffinbooking.ui.application.AppConfig
import com.tiffinbooking.ui.extension.DatePickerDialogBox
import com.tiffinbooking.ui.extension.checkNull
import com.tiffinbooking.ui.extension.emailValidation
import com.tiffinbooking.ui.extension.showMessage
import com.tiffinbooking.ui.model.OrderModel
import com.tiffinbooking.ui.theme.TiffinBookingTheme
import com.tiffinbooking.ui.theme.gray
import com.tiffinbooking.ui.theme.orange
import com.tiffinbooking.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookingScreen(
    navController: NavController,
    image: Int,
    name: String,
    standard: String,
    mini: String,
    type: String
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val mealTimeList = arrayListOf<String>().apply {
        clear()
        add("Lunch")
        add("Dinner")
        add("Both")
    }
    var mealTime by remember { mutableStateOf("") }

    val mealTypeList = arrayListOf<String>().apply {
        clear()
        add("Veg")
        add("Non-Veg")
    }
    var mealType by remember { mutableStateOf("") }

    val mealPlanList = arrayListOf<String>().apply {
        clear()
        add("Mini")
        add("Standard")
    }
    var mealPlan by remember { mutableStateOf("") }

    val durationList = arrayListOf<String>().apply {
        clear()
        add("3 Days")
        add("1 week")
        add("1 month")
        add("3 months")
    }
    var duration by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf(0) }

    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    val nestedScroll = rememberScrollState()
//    var name by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
    var booked by remember { mutableStateOf(false) }

    fun validation(): Boolean {
        return /*if (!checkNull(name.toString().trim())) {
            context.showMessage("Please enter name.")
            false
        } else if (!checkNull(email.toString().trim())) {
            context.showMessage("Please enter email address.")
            false
        } else if (emailValidation(email.trim())) {
            context.showMessage("Please enter valid user email address.")
            false
        } else if (!checkNull(phone.toString().trim())) {
            context.showMessage("Please enter phone number.")
            false
        } else if (phone.length != 10) {
            context.showMessage("Phone number should be 10 digit.")
            false
        } else if (!checkNull(address.toString().trim())) {
            context.showMessage("Please enter address.")
            false
        } else */if (!checkNull(mealTime.toString().trim())) {
            context.showMessage("Please select meal time.")
            false
        } else if (!checkNull(mealType.toString().trim())) {
            context.showMessage("Please select meal type.")
            false
        } else if (!checkNull(mealPlan.toString().trim())) {
            context.showMessage("Please select meal plan.")
            false
        } else if (!checkNull(duration.toString().trim())) {
            context.showMessage("Please select duration.")
            false
        } else if (quantity <= 0) {
            context.showMessage("Please select quantity and Quantity show be grater than 0.")
            false
        } else if (!checkNull(selectedDate.toString().trim())) {
            context.showMessage("Please select date.")
            false
        } else {
            true
        }
    }

    var price = 0
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
                        .verticalScroll(nestedScroll)
                        .background(color = white)
                ) {
                    /*Text(
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
                    Spacer(modifier = Modifier.height(20.dp))*/
                    Text(
                        text = "Select Meal Time : ", color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        mealTimeList.forEach { name ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (name == mealTime),
                                    onClick = { mealTime = name },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)

                                )
                                Text(
                                    text = name,
                                    style = TextStyle(color = Color.Black),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable {
                                            mealTime = name
                                        }
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Select Meal Type : ", color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        mealTypeList.forEach { name ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (name == mealType),
                                    onClick = { mealType = name },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)

                                )
                                Text(
                                    text = name,
                                    style = TextStyle(color = Color.Black),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable {
                                            mealType = name
                                        }
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Select Meal Plan : ", color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        mealPlanList.forEach { name ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (name == mealPlan),
                                    onClick = { mealPlan = name },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)

                                )
                                Text(
                                    text = name,
                                    style = TextStyle(color = Color.Black),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable {
                                            mealPlan = name
                                        }
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Select Duration : ", color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        durationList.forEach { name ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (name == duration),
                                    onClick = { duration = name },
                                    colors = RadioButtonDefaults.colors(selectedColor = Color.Black)

                                )
                                Text(
                                    text = name,
                                    style = TextStyle(color = Color.Black),
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable {
                                            duration = name
                                        }
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Select Quantity : ", color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    Row(
                        modifier = Modifier
                            .border(BorderStroke(1.dp, colorResource(id = R.color.black)))
                            .padding(10.dp), horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.minus),
                            modifier = Modifier
                                .width(30.dp)
                                .height(36.dp)
                                .padding(5.dp)
                                .clickable {
                                    if (quantity > 0) {
                                        quantity--
                                    } else {
                                        quantity = 0
                                    }

                                },
                            contentDescription = "Expandable Image"
                        )
                        Text(
                            "$quantity",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            modifier = Modifier
                                .width(36.dp)
                                .height(36.dp)
                                .padding(5.dp)
                                .clickable {
                                    quantity++
                                },
                            contentDescription = "Expandable Image"
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Select Date : ", color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp
                        )
                    )
                    OutlinedTextField(
                        value = if (selectedDate != "") selectedDate else "Select Date",
                        shape = RoundedCornerShape(10.dp),
                        visualTransformation = VisualTransformation.None,
                        enabled = false,
                        readOnly = false,
                        maxLines = 1,
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black, fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 0.dp)
                            .fillMaxWidth()
                            .focusable(false)
                            .clickable(onClick = {
                                showDatePicker = true
                            }),
                        onValueChange = {
                            selectedDate = it
                        },
                        placeholder = {
                            Text("Select Date", color = Color.Black, fontSize = 16.sp)
                        }
                    )

                    if (showDatePicker) {
                        context.DatePickerDialogBox(onDateSelect = {
                            selectedDate = it
                            showDatePicker = false
                        })
                    }
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
                                price = if (mealPlan == "Standard")
                                    standard.toInt() * quantity
                                else mini.toInt() * quantity
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
                                fontWeight = FontWeight.Medium,
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
                text = { Text("Your tiffin has been booked successfully.\n\nTotal Cost : $price Gbp") },
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
                            AppConfig.list.add(
                                OrderModel(
                                    image = image,
                                    price = price,
                                    name = name,
                                    mealTime = mealTime,
                                    mealType = mealType,
                                    mealPlan = mealPlan,
                                    duration = duration,
                                    date = selectedDate,
                                    quantity = quantity
                                )
                            )
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
                                fontWeight = FontWeight.Medium,
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
