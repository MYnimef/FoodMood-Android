package com.mynimef.foodmood.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.ui.theme.FoodMoodTheme

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        MyMavHost(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.9f),
            navController = navController
        )
        MyNavigationBar(
            modifier = Modifier
                .fillMaxWidth(),
            navController = navController
        )
    }
}

@Composable
fun MyNavigationBar(modifier: Modifier, navController: NavHostController) {
    NavigationBar(
        modifier = modifier
    ) {
        val bottomNavItems = listOf(
            BottomNavigationItem.Home,
            BottomNavigationItem.Create,
            BottomNavigationItem.Settings,
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = {
                    Icon (
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MyMavHost(modifier: Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen() }
        composable("create") { CreateScreen() }
        composable("settings") { SettingsScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodMoodTheme {
        NavigationScreen()
    }
}