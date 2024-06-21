package com.example.flexzone

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

//The Main composable
@Composable
fun UserProfileScreen(navController: NavController) {
    Surface(
        color = colorResource(id = R.color.white),
        modifier = Modifier.fillMaxSize()
    )
    {LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        )
    {
    item {
          Box(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(bottom = 5.dp, end = 5.dp)
            )
          {
              Image(
                  painter = painterResource(id = R.drawable.suppimglogo),
                  contentDescription = "App Logo",
                  modifier = Modifier
                      .align(Alignment.Center)
                      .size(80.dp))
                }
            }
        item {
            UserProfileHeading()
        }
        item {
            Spacer(modifier = Modifier.height(36.dp))
        }
        item {
            UserProfileInformation()
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
        item {
            UserProfileUpdate()
        }
        }
    }
}

//The HEading for the User Profile Page
@Composable
fun UserProfileHeading()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
                Text(text = "My Account", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold,
                    color = Color.Black)
            }
}

//The main color box the User Profile
@Composable
fun UserProfileInformation() {
    val gradientcolorone = colorResource(id = R.color.gradientcolorone)
    val gradientcolortwo = colorResource(id = R.color.gradientcolortwo)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(8.dp),
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(colors = listOf(gradientcolorone, gradientcolortwo))
                )
                .padding(horizontal = 30.dp, vertical = 30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.myimgg),
                    contentDescription = "User Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = stringResource(id = R.string.user_name),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.user_email),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.user_add),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}



//The User Options when a person is logged into the system
@Composable
fun UserOptions(
    icon: ImageVector,
    text: String,
    endIcon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFF0047AB))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Profile User Icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                imageVector = endIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

//The User Options when a person is logged into the system
@Composable
fun UserProfileUpdate() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    {
        UserOptions(
            icon = Icons.Default.Person,
            text = stringResource(id = R.string.edit_profile),
            endIcon = Icons.Default.ArrowForward,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        UserOptions(
            icon = Icons.Default.LocationOn,
            text = stringResource(id = R.string.user_address),
            endIcon = Icons.Default.ArrowForward,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        UserOptions(
            icon = Icons.Default.ShoppingCart,
            text = stringResource(id = R.string.user_orders),
            endIcon = Icons.Default.ArrowForward,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        UserOptions(
            icon = Icons.Default.PlayArrow,
            text = stringResource(id = R.string.user_payment),
            endIcon = Icons.Default.ArrowForward,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        UserOptions(
            icon = Icons.Default.Settings,
            text = stringResource(id = R.string.user_settings),
            endIcon = Icons.Default.ArrowForward,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        UserOptions(icon = Icons.Default.ExitToApp,
            text = stringResource(id = R.string.user_logout),
            endIcon = Icons.Default.ArrowForward,
            modifier = Modifier.padding(bottom = 4.dp),
            //onClick = { navController.navigate("homeProfile") }
        )
    }
}
