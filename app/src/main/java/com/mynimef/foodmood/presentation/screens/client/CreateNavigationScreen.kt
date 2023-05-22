package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.data.models.enums.EAuthNavigation
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.data.models.enums.EFoodType
import com.mynimef.foodmood.presentation.screens.shared.SignUpScreenFirst
import com.mynimef.foodmood.presentation.screens.shared.SignUpScreenSecond
import com.mynimef.foodmood.presentation.screens.shared.SignUpScreenThird
import com.mynimef.foodmood.presentation.screens.shared.SignUpViewModel
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateNavigationScreen() {
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
    val navigateTo = { route: String ->
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val viewModel: CreateViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "first"
    ) {
        composable("first") { CardsChoiceScreen(
            navigateTo = navigateTo,
            viewModel = viewModel,
        ) }
        composable("second") { EmotionCardScreen()
        }
    }
}

@Preview
@Composable
private fun CreateNavScreenPreview() {
    FoodMoodTheme {
        CreateNavigationScreen()
    }
}