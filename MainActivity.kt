package com.example.flexzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.flexzone.data.BottomNavItem
import com.example.flexzone.ui.theme.AllCategoriesScreen
import com.example.flexzone.ui.theme.FlexzoneTheme
import java.lang.reflect.Modifier
//The Enumerators
enum class SupplementScreen {
    Login,
    SignUp,
    Home,
    Search,
    Cart,
    Profile,
    Protein
}
//The calling of the application
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlexzoneTheme {
                SupplementApp()
            }
        }
    }
}
//The Composable for the Top Bar Application
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupplementAppBar(
    currentScreen: SupplementScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name), color = Color.White) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.productBlue)
        ),
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
        navigationIcon = {
            if (canNavigateBack) {
                androidx.compose.material.IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.app_name),
                        tint = Color.White
                    )
                }
            }
        }
    )
}

//The Composable for the Bottom Navigation Bar
@Composable
fun SupplementNavBar(navController: NavController, items: List<BottomNavItem>) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.productBlue),
        modifier = androidx.compose.ui.Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = { androidx.compose.material.Icon(item.icon, contentDescription = null) },
                label = { androidx.compose.material.Text(item.label) },
                selectedContentColor = Color.Yellow,
                unselectedContentColor = Color.White
            )
        }
    }
}

fun isTopBarAndBottomBarVisible(currentScreen: String): Boolean {
    return currentScreen != SupplementScreen.Login.name && currentScreen != SupplementScreen.SignUp.name
}


//The Composable to call the navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupplementApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val bottomNavigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    val supplementScreen = SupplementScreen.valueOf(
        backStackEntry?.destination?.route ?: SupplementScreen.Home.name
    )
    val isBarsVisible = isTopBarAndBottomBarVisible(supplementScreen.name)
    Scaffold(
        topBar = {
            if (isBarsVisible) {
                SupplementAppBar(
                    currentScreen = supplementScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        },
        bottomBar = {
            if (isBarsVisible) {
                SupplementNavBar(navController, bottomNavigationItems)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SupplementScreen.Login.name,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(route = SupplementScreen.Login.name) {
                LoginScreen(
                    onLoginClicked = {
                        navController.navigate(SupplementScreen.Home.name)
                    },
                    navController = navController
                )
            }
            composable(route = SupplementScreen.SignUp.name) {
                SignUpScreen(navController = navController)
            }

            composable(route = SupplementScreen.Home.name) {
                HomeScreen(navController = navController)
            }

            composable(route = SupplementScreen.Search.name) {
                AllCategoriesScreen(navController = navController)
            }

            composable(route = SupplementScreen.Cart.name) {
                ShoppingCartScreen(navController = navController)
            }

            composable(route = SupplementScreen.Profile.name) {
                UserProfileScreen(navController = navController)
            }

            composable(route = SupplementScreen.Protein.name) {
                ProductDescription(navController = navController)
            }
        }
    }
}







