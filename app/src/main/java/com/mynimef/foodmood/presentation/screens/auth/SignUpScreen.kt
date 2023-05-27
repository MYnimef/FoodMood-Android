package com.mynimef.foodmood.presentation.screens.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.data.models.enums.ENavigationAuth

@Composable
fun SignUpScreen(
    navigateTo: (route: ENavigationAuth) -> Unit,
) {
    val navController = rememberNavController()
    MyMavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        parentNavigateTo = navigateTo,
    )
}

@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController,
    parentNavigateTo: (route: ENavigationAuth) -> Unit,
) {
    val navigateTo = { route: String ->
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }
    val viewModel: SignUpViewModel = viewModel()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "first"
    ) {
        composable("first") { SignUpScreenFirst(
            parentNavigateTo = parentNavigateTo,
            navigateTo = navigateTo,
            viewModel = viewModel,
        ) }
        composable("second") { SignUpScreenSecond(
            navigateTo = navigateTo,
            viewModel = viewModel,
        ) }
        composable("third") { SignUpScreenThird(
            navigateTo = navigateTo,
            viewModel = viewModel,
        ) }
        composable("fourth") { SignUpScreenFourth(
            navigateTo = navigateTo,
            viewModel = viewModel,
        ) }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {

}