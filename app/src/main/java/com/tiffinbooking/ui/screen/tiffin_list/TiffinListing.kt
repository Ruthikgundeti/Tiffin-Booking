package com.tiffinbooking.ui.screen.tiffin_list

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tiffinbooking.R
import com.tiffinbooking.ui.common.header.Header
import com.tiffinbooking.ui.extension.tiffinData
import com.tiffinbooking.ui.localdatabase.TiffinDatabase
import com.tiffinbooking.ui.model.DataModel
import com.tiffinbooking.ui.theme.TiffinBookingTheme
import com.tiffinbooking.ui.theme.orange
import com.tiffinbooking.ui.theme.white
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TiffinListing(navController: NavController, preference: TiffinDatabase) {
    val context = LocalContext.current
    val nestedScroll = rememberScrollState()
    val list = ArrayList<DataModel>()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var signOut by remember { mutableStateOf(false) }
    list.addAll(context.tiffinData())
    TiffinBookingTheme {
        androidx.compose.material.Scaffold(
            topBar = {
                Header(
                    navController = navController,
                    onButtonClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = orange),
            drawerContent = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .background(color = orange)
                        .fillMaxWidth()
                ) {

                    Image(
                        painterResource(id = R.drawable.tiffin),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(150.dp)
                    )
                    Spacer(modifier = Modifier)

                    Text(
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        color = white,
                    )
                }
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                                signOut = true
                            }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        androidx.compose.material.Text(text = "Logout", color = white)
                    }
                }
            },
            backgroundColor = orange,
            contentColor = orange,
            drawerBackgroundColor = orange
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = white)
                    .verticalScroll(nestedScroll)
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                list.forEachIndexed { index, model ->
                    Card(
                        modifier = Modifier
                            .padding(bottom = 20.dp, start = 10.dp, end = 10.dp)
                            .background(color = white)
                            .fillMaxWidth()
                            .height(300.dp)
                            .clickable {
                                navController.navigate("TiffinDetail" + "/${model.image}" + "/${model.name}" + "/${model.price}" + "/${model.detail}")
                            },
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
                            model.name ?: "",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                        Text(
                            "${model.price}",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                    }
                }
            }
        }
        if (signOut) {
            AlertDialog(
                onDismissRequest = {
                    signOut = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
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
                            signOut = false
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
                                text = "Cancel",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                },
                dismissButton = {
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
                            preference.isLogin = false
                            navController.navigate(
                                "UserLogin"
                            ) {
                                popUpTo("TiffinListing") {
                                    inclusive = true
                                }
                            }
                            signOut = false
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
                                text = "Logout",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }

                }
            )
        }
    }
}

