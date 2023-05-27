package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavigationClient
import com.mynimef.foodmood.data.models.enums.ENavigationCreate
import com.mynimef.foodmood.data.models.enums.ENavigationSettings
import com.mynimef.foodmood.presentation.elements.MyIcon
import com.mynimef.foodmood.presentation.screens.client.SettingsViewModel
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
        composable(ENavigationSettings.SETTINGS_LIST.value) { SettingsStartScreen(navigateTo = navigateTo) }
        composable(ENavigationSettings.USER_INFO.value) { UserInfoScreen() }
        composable(ENavigationSettings.PREFERENCES.value) { PrefSettingsScreen(navigateTo = navigateTo)}
        composable(ENavigationSettings.NOTIFICATION.value) { NotificationsScreen()}
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    FoodMoodTheme {
        SettingsScreen()
    }
}