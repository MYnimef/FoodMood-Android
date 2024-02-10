package com.mynimef.foodmood.screens.client

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
import com.mynimef.data.enums.ENavClientMain
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
            CreateScreen_1(viewModel = it.sharedViewModel(navController))
        }
        composable(
            route = ENavClientMain.CREATE_EMOTION_CARD.route
        ) {
            CreateScreen_2(viewModel = it.sharedViewModel(navController))
        }
    }
    composable(
        route = ENavClientMain.BOTTOM_BAR.route
    ) {
        ClientBottomBarScreen()
    }

    composable(
        route = ENavClientMain.SET_UP_ACCOUNT.route
    ) {
        SetUpAccountScreen()
    }
    composable(
        route = ENavClientMain.SET_UP_PREFERENCES.route
    ) {
        SetUpPreferencesScreen()
    }
    composable(
        route = ENavClientMain.SET_UP_NOTIFICATIONS.route
    ) {
        SetUpNotificationsScreen()
    }
}

