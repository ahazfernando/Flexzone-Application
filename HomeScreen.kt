package com.example.flexzone

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//Main Composable of the Home Screen

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.myimgg),
                                contentDescription = "My Image",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = stringResource(id = R.string.greeting),
                                    color = colorResource(id = R.color.black),
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = stringResource(id = R.string.user_name),
                                    color = colorResource(id = R.color.black),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(bottom = 5.dp, end = 5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.parentlogoversionone),
                                contentDescription = "App Logo",
                                modifier = Modifier.size(80.dp)
                            )
                        }
                    }
                }
                item {
                    Image(
                        painter = painterResource(R.drawable.fleximg002),
                        contentDescription = "Home Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                            .clip(shape = RoundedCornerShape(20.dp))
                            .padding(top = 12.dp)
                            .clickable { navController.navigate(SupplementScreen.Protein.name) }
                    )
                }
                item {
                    CategoriesScreen()
                }
                items(1) {
                    Products(navController)
                }
            }
        }
    }
}

@Composable
fun CategoriesScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Categories",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )
            Text(
                text = "See all",
                color = Color.Blue,
                fontSize = 14.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryButton(text = stringResource(id = R.string.all))
            CategoryButton(text = stringResource(id = R.string.protein))
            CategoryButton(text = stringResource(id = R.string.creatine))
            CategoryButton(text = stringResource(id = R.string.cereal))
        }
        HomeHeading()
    }
}

@Composable
fun CategoryButton(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFF0047AB), shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 2.dp, vertical = 1.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun HomeHeading() {
    Column {
        Text(
            text = stringResource(R.string.product_heading),
            style = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 20.dp, bottom = 2.dp)
        )
        Text(
            text = stringResource(R.string.product_para),
            style = androidx.compose.ui.text.TextStyle(
                color = Color.Black,
                fontSize = 12.sp,
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Composable
fun Products(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val productImages = listOf(
        R.drawable.fleximg010,
        R.drawable.fleximg011,
        R.drawable.fleximg012,
        R.drawable.fleximg013,
        R.drawable.fleximg014,
        R.drawable.fleximg015
    )

    val productTitles = listOf(
        "Optimum Nutrition 2.2LBS",
        "Platinum Creatine - 300G",
        "Kevin Levrone - 300G",
        "ISO 100 - 2.2LBS",
        "Grenade Chocolate - 1KG",
        "Applied Nutrition Whey - 2.2LBS"
    )
    val productPrices = listOf(
        "Rs 37500",
        "Rs 8500",
        "Rs 9500",
        "Rs 25500",
        "Rs 17500",
        "Rs 22500"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (isLandscape) {//This is the place where the landscape mode of the login screen happens
            for (i in productImages.indices step 3) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ProductItem(
                        imageResId = productImages[i],
                        title = productTitles[i],
                        price = productPrices[i],
                        navController = navController
                    )
                    if (i + 1 < productImages.size) {
                        ProductItem(
                            imageResId = productImages[i + 1],
                            title = productTitles[i + 1],
                            price = productPrices[i + 1],
                            navController = navController
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    if (i + 2 < productImages.size) {
                        ProductItem(
                            imageResId = productImages[i + 2],
                            title = productTitles[i + 2],
                            price = productPrices[i + 2],
                            navController = navController
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        } else {
            for (i in productImages.indices step 2) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ProductItem(
                        imageResId = productImages[i],
                        title = productTitles[i],
                        price = productPrices[i],
                        navController = navController
                    )
                    if (i + 1 < productImages.size) {
                        ProductItem(
                            imageResId = productImages[i + 1],
                            title = productTitles[i + 1],
                            price = productPrices[i + 1],
                            navController = navController
                        )
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
//This part would not be there when in landscape
@Composable
fun ProductItem(imageResId: Int, title: String, price: String, navController: NavController) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.LightGray)
                .clickable { navController.navigate(SupplementScreen.Protein.name) }
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = price,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Add to Cart",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
                    .clickable { navController.navigate("shopping_cart") }
            )
        }
    }
}




