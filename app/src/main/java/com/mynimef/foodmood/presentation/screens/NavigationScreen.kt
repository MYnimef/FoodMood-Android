package com.mynimef.foodmood.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.presentation.BottomNavigationItem
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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
fun MyNavigationBar(
    modifier: Modifier,
    navController: NavHostController
) {
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

        Row(
            modifier = modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavItems.forEach { item ->
                MyNavigationItem(
                    drawableId = item.icon,
                    selected = currentRoute == item.route,
                    normalColor = Color.Black,
                    selectedColor = Color.Black
                ) {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}

@Composable
fun MyNavigationItem(
    drawableId: Int,
    selected: Boolean,
    normalColor: Color,
    selectedColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = CircleShape)
            .fillMaxHeight(0.7f)
            .aspectRatio(1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon (
            modifier = Modifier
                .fillMaxSize(0.7f),
            painter = painterResource(id = drawableId),
            contentDescription = null
        )
    }
}

@Composable
fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController
) {
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