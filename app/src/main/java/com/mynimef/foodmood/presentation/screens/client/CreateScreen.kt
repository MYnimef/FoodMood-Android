package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.data.models.enums.ENavigationCreate
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun CreateScreen() {
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
    val navigateTo = { route: ENavigationCreate ->
        navController.navigate(route.value) {
            launchSingleTop = true
            restoreState = true
        }
    }

    val viewModel: CreateViewModel = viewModel()
    viewModel.navigation = navigateTo

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ENavigationCreate.PICK_TYPE.value
    ) {
        composable(ENavigationCreate.PICK_TYPE.value) { CreateScreenFirst(viewModel) }
        composable(ENavigationCreate.ADD_CARD.value) { CreateScreenSecond(viewModel) }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateScreenPreview() {
    FoodMoodTheme {
        CreateScreen()
    }
}