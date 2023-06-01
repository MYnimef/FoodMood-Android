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
import com.mynimef.foodmood.data.models.enums.ENavigationSettings
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun SettingsScreen() {
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
    val navigateTo = { route: ENavigationSettings ->
        navController.navigate(route.value) {
            launchSingleTop = true
            restoreState = true
        }
    }

    val viewModel: SettingsViewModel = viewModel()
    viewModel.navigation = navigateTo

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ENavigationSettings.SETTINGS_LIST.value
    ) {
       // composable(ENavigationSettings.SETTINGS_LIST.value) { SettingsScreenStart(navigateTo = navigateTo) }
        composable(ENavigationSettings.PREFERENCES.value) { SettingsScreenPreferences(navigateTo = navigateTo)}
        composable(ENavigationSettings.NOTIFICATION.value) { SettingsScreenNotification()}
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    FoodMoodTheme {
        SettingsScreen()
    }
}