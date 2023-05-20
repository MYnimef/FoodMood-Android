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
import com.mynimef.foodmood.data.models.enums.EMainNavigation

@Composable
fun SignUpScreen(
    navigateTo: (route: EMainNavigation) -> Unit,
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
    parentNavigateTo: (route: EMainNavigation) -> Unit,
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
            navigateTo = navigateTo,
            viewModel = viewModel,
        ) }
        composable("second") { SignUpScreenSecond(
            navigateTo = parentNavigateTo,
            viewModel = viewModel,
        ) }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {

}