package com.mynimef.foodmood.screens.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.mynimef.domain.models.enums.ENavAuth
import com.mynimef.foodmood.extensions.sharedViewModel

@Composable
fun AuthMainScreen() {
    val viewModel: AuthMainViewModel = hiltViewModel()
    val navController = rememberNavController()

    LaunchedEffect("auth_main") {
        var previous = ENavAuth.SIGN_IN
        viewModel.navigation.collect { next ->
            navController.navigate(next.route) {
                if (next.parentRoute == null && previous.parentRoute != null)
                    popUpTo(previous.parentRoute!!) { inclusive = true }

                launchSingleTop = true
                restoreState = true

                previous = next
            }
        }
    }

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = ENavAuth.SIGN_IN.route,
    ) {
        authMain(navController)
    }
}

private fun NavGraphBuilder.authMain(navController: NavController) {
    composable(route = ENavAuth.SIGN_IN.route) {
        SignInScreen()
    }
    navigation(
        startDestination = ENavAuth.SIGN_UP_1.route,
        route = ENavAuth.SIGN_UP.route
    ) {
        composable(route = ENavAuth.SIGN_UP_1.route) {
            SignUpScreen_1(viewModel = it.sharedViewModel(navController))
        }
        composable(route = ENavAuth.SIGN_UP_2.route) {
            SignUpScreen_2(viewModel = it.sharedViewModel(navController))
        }
        composable(route = ENavAuth.SIGN_UP_3.route) {
            SignUpScreen_3(viewModel = it.sharedViewModel(navController))
        }
        composable(route = ENavAuth.SIGN_UP_4.route) {
            SignUpScreen_4(viewModel = it.sharedViewModel(navController))
        }
    }
}