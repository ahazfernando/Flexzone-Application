package com.example.flexzone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//Main Composable of the ShoppingCartScreen
@Composable
fun ShoppingCartScreen(navController: NavController) {
    val totalPriceState = remember { mutableStateOf(0f) }

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, end = 5.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.suppimglogo),
                        contentDescription = "App Logo",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(80.dp)
                    )
                }
            }
            item {
                CartHeading()
            }
            item {
                ShoppingCartProducts { price ->
                    totalPriceState.value += price
                }
            }
            item {
                ShoppingCartSummary(totalPrice = totalPriceState.value)
            }

        }
    }
}
//The Heading of the Cart Page
@Composable
fun CartHeading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.cart_heading),
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        Text(
            text = stringResource(id = R.string.cart_caption),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
// The Products added to cart by the user
@Composable
fun ShoppingCartProducts(onPriceChange: (Float) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ProductItem(
            productName = "Platinum Creatine - 300G",
            price = 8500f,
            imageResId = R.drawable.fleximg011,
            onPriceChange = onPriceChange,
            textColor = Color.Black
        )
        ProductItem(
            productName = "Kevin Levrone - 300G",
            price = 9500f,
            imageResId = R.drawable.fleximg012,
            onPriceChange = onPriceChange,
            textColor = Color.Black
        )
        ProductItem(
            productName = "Grenade Chocolate - 1KG",
            price = 17500f,
            imageResId = R.drawable.fleximg014,
            onPriceChange = onPriceChange,
            textColor = Color.Black
        )
    }
}

@Composable
fun ProductItem(
    productName: String,
    price: Float,
    imageResId: Int,
    onPriceChange: (Float) -> Unit,
    textColor: Color
) {
    var quantity by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Product Image",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = productName,
                    color = textColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_remove),
                        contentDescription = "Remove",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                if (quantity > 0) {
                                    quantity--
                                    onPriceChange(-price)
                                }
                            }
                    )
                    Text(
                        text = "$quantity",
                        color = textColor,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                quantity++
                                onPriceChange(price)
                            }
                    )
                }
                val totalPrice = price * quantity
                Text(
                    text = "Total Price: Rs ${String.format("%.2f", totalPrice)}",
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

//The Composable to calculate the total including an added discount
@Composable
fun ShoppingCartSummary(totalPrice: Float) {
    val discountThreshold = 25000
    val discountPercentage = 0.15f

    val discount = if (totalPrice > discountThreshold) {
        totalPrice * discountPercentage
    } else {
        0f
    }

    val finalPrice = totalPrice - discount

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Total Price: Rs ${String.format("%.2f", totalPrice)}",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (totalPrice > discountThreshold) {
            Text(
                text = "Discount Given: Rs ${String.format("%.2f", discount)}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = "Final Price: Rs ${String.format("%.2f", finalPrice)}",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
    CheckOutButton()
}
@Composable
fun CheckOutButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFD22B2B), shape = RoundedCornerShape(16.dp))
            .padding(2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD22B2B), shape = RoundedCornerShape(14.dp))
                .clickable { /* TODO*/ }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.checkout),
                    fontSize = 16.sp,
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Proceed Arrow",
                    tint = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}




