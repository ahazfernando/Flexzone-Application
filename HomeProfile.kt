package com.example.flexzone

import android.content.res.Configuration
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(onLoginClicked: () -> Unit, navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLandscape) {//This is the place where the landscape mode is fixed and altered
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.parentlogoversionone),
                            contentDescription = "Flexzone Logo",
                            modifier = Modifier
                                .size(350.dp)
                                .padding(end = 16.dp)
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = stringResource(id = R.string.login),
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            OutlinedTextField(
                                value = username,
                                onValueChange = { username = it.filter { char -> char != ' ' } },
                                label = { Text(stringResource(id = R.string.user_login_pg)) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text(stringResource(R.string.password)) },
                                visualTransformation = PasswordVisualTransformation(),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            )
                            Button(
                                onClick = { onLoginClicked() },
                                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.productBlue)),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp)
                            ) {
                                Text(stringResource(R.string.login), color = colorResource(id = R.color.white))
                            }
                            Text(
                                text = stringResource(R.string.register_link),
                                style = androidx.compose.ui.text.TextStyle(color = Color.Blue),
                                modifier = Modifier.clickable { navController.navigate(SupplementScreen.SignUp.name) }
                            )
                        }
                    }
                }
            } else {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.parentlogoversionone),
                            contentDescription = "Flexzone Logo",
                            modifier = Modifier
                                .size(350.dp)
                                .padding(bottom = 16.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                            ),
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it.filter { char -> char != ' ' } },
                            label = { Text(stringResource(id = R.string.user_login_pg)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(stringResource(R.string.password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = { onLoginClicked() },
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.productBlue)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            Text(stringResource(R.string.login), color = colorResource(id = R.color.white))
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.register_link),
                            style = androidx.compose.ui.text.TextStyle(color = Color.Blue),
                            modifier = Modifier.clickable { navController.navigate(SupplementScreen.SignUp.name) }
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.iglogo),
                                contentDescription = "Instagram",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.fblogo),
                                contentDescription = "Facebook",
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(end = 8.dp)
                            )
                            Image(
                                painter = painterResource(R.drawable.walogo),
                                contentDescription = "WhatsApp",
                                modifier = Modifier
                                    .size(70.dp)
                                    .padding(end = 8.dp)
                                    .padding(bottom = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}




