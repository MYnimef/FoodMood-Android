package com.mynimef.foodmood.presentation.screens.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.data.models.enums.EAppState
import com.mynimef.foodmood.data.models.enums.EMainNavigation
import com.mynimef.foodmood.presentation.screens.client.ClientNavigationScreen
import com.mynimef.foodmood.presentation.screens.trainer.TrainerNavigationScreen
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()

    val navController = rememberNavController()
    MyMavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        viewModel.getAppState(),
    )
}

@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController,
    appState: EAppState,
) {
    val navigateTo = { route: EMainNavigation ->
        navController.navigate(route.value) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = when(appState) {
            EAppState.NONE -> EMainNavigation.LOGIN.value
            EAppState.CLIENT -> EMainNavigation.CLIENT.value
            EAppState.TRAINER -> EMainNavigation.TRAINER.value
        }
    ) {
        composable(EMainNavigation.LOGIN.value) { SignInScreen(navigateTo) }
        composable(EMainNavigation.SIGNUP.value) { SignUpScreen(navigateTo) }
        composable(EMainNavigation.CLIENT.value) { ClientNavigationScreen() }
        composable(EMainNavigation.TRAINER.value) { TrainerNavigationScreen() }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    FoodMoodTheme {
        MainScreen()
    }
}