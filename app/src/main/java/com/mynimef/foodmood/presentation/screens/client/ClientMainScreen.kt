package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.extensions.OnLifecycleEvent
import com.mynimef.foodmood.extensions.sharedViewModel

@Composable
fun ClientMainScreen() {
    val viewModel: ClientMainViewModel = viewModel()
    val navController = rememberNavController()

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            viewModel.initClient()
        }
    }

    LaunchedEffect("client_main") {
        var previous = ENavClientMain.BOTTOM_BAR
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
        startDestination = ENavClientMain.BOTTOM_BAR.route,
    ) { clientMain(navController) }
}

private fun NavGraphBuilder.clientMain(navController: NavController) {
    navigation(
        startDestination = ENavClientMain.CREATE_PICK_TYPE.route,
        route = ENavClientMain.CREATE.route
    ) {
        composable(
            route = ENavClientMain.CREATE_PICK_TYPE.route
        ) {
            CreateScreenFirst(viewModel = it.sharedViewModel(navController))
        }
        composable(
            route = ENavClientMain.CREATE_EMOTION_CARD.route
        ) {
            CreateScreenSecond(viewModel = it.sharedViewModel(navController))
        }
    }
    composable(
        route = ENavClientMain.BOTTOM_BAR.route
    ) {
        ClientBottomBarScreen()
    }

    composable(
        route = ENavClientMain.SETTINGS_USER_INFO.route
    ) {
        SettingsScreenUser()
    }
    composable(
        route = ENavClientMain.SETTINGS_PREFERENCES.route
    ) {
        SettingsScreenPreferences()
    }
    composable(
        route = ENavClientMain.SETTINGS_NOTIFICATIONS.route
    ) {
        SettingsScreenNotification()
    }

}

