package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.data.models.enums.ENavigationAuth

@Composable
fun AuthNavigationScreen() {
    val navController = rememberNavController()
    MyMavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
    )
}

@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController,
) {
    val navigateTo = { route: ENavigationAuth ->
        navController.navigate(route.value) {
            launchSingleTop = true
            restoreState = true
        }
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ENavigationAuth.SIGN_IN.value
    ) {
        composable(ENavigationAuth.SIGN_IN.value) { SignInScreen(
            navigateTo = navigateTo
        ) }
        composable(ENavigationAuth.SIGN_UP.value) { SignUpScreen(
            navigateTo = navigateTo
        ) }
    }
}