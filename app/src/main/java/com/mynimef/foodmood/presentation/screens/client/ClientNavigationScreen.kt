package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun ClientNavigationScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationItem(icon = R.drawable.ic_home, route = "home"),
        BottomNavigationItem(icon = R.drawable.ic_create, route = "create"),
        BottomNavigationItem(icon = R.drawable.ic_settings, route = "settings"),
    )

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
            navController = navController,
            bottomNavItems
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
        composable("home") { com.mynimef.foodmood.presentation.screens.trainer.HomeScreen() }
        composable("create") { com.mynimef.foodmood.presentation.screens.trainer.CreateScreen() }
        composable("settings") { com.mynimef.foodmood.presentation.screens.trainer.SettingsScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun ClientNavigationScreenPreview() {
    FoodMoodTheme {
        ClientNavigationScreen()
    }
}