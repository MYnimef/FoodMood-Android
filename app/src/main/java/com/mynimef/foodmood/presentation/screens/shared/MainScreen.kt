package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.presentation.screens.client.ClientNavigationScreen
import com.mynimef.foodmood.presentation.screens.trainer.TrainerNavigationScreen
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    MyMavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController
    )
}

@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    val navigateTo = { route: String ->
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navigateTo) }
        composable("client") { ClientNavigationScreen() }
        composable("trainer") { TrainerNavigationScreen() }
        composable("signup") { SignupScreen(navigateTo) }
        composable("preferences") { PreferncesScreen(navigateTo) }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    FoodMoodTheme {
        MainScreen()
    }
}