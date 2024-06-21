package com.example.flexzone

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.format.TextStyle

//The Page to display the Product Information
@Composable
fun ProductDescription(navController: NavController) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                ) {
                    item {
                        ProductImage()
                    }
                }
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        ProductHeading()
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        PurchaseButton {}
                    }
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        ProductPurchaseInfo()
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, end = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(55.dp)
                                .padding(start = 16.dp)
                                .clickable { navController.popBackStack() }
                        )
                        Image(
                            painter = painterResource(id = R.drawable.suppimglogo),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(60.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Shopping Cart",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable { navController.navigate("shopping_cart") }
                        )
                    }
                }
                item {
                    ProductImage()
                }
                item {
                    ProductHeading()
                }
                item {
                    PurchaseButton {}
                }
                item {
                    ProductPurchaseInfo()
                }
            }
        }
    }
}
//The image of the product
@Composable
fun ProductImage() {
    val ProductColor = LocalContext.current.resources.getColor(R.color.GrayProduct)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(ProductColor)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fleximg010),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(200.dp)
            )
        }
    }
}
//The Heading/name of the product
@Composable
fun ProductHeading() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Text(
            text = stringResource(id = R.string.Product_Name),
            fontSize = 36.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 300.dp),
            style = androidx.compose.ui.text.TextStyle(lineHeight = 40.sp)
        )
        Text(
            text = stringResource(id = R.string.product_description),
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
//The Add to Cart Button
@Composable
fun PurchaseButton(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(
                color = colorResource(id = R.color.productBlue),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Text(
            text = stringResource(id = R.string.cart_text),
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(10.dp)
        )
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Shopping Cart",
            tint = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}
//The Information for the product
@Composable
fun ProductPurchaseInfo() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
    ) {
        Text(
            text = stringResource(id = R.string.product_price),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 1.dp)
        )
        Text(
            text = stringResource(id = R.string.product_quantity),
            fontSize = 16.sp,
            color = colorResource(id = R.color.quantity_color),
            modifier = Modifier.padding(top = 1.dp)
        )
    }
}





