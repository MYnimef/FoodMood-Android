package com.mynimef.foodmood.presentation.screens.client

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.R
import com.mynimef.foodmood.data.models.enums.ENavClientBottomBar
import com.mynimef.foodmood.data.models.enums.ENavClientMain
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar

@Composable
fun ClientBottomBarScreen() {
    val viewModel: ClientBottomBarViewModel = viewModel()
    val navController = rememberNavController()

    LaunchedEffect("client_main") {
        viewModel.navigation.collect {
            navController.navigate(it.route) {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    val navItems = listOf(
        getItem(
            nav = ENavClientBottomBar.HOME,
            navigate = viewModel::switchScreen
        ),
        getItem(
            nav = ENavClientBottomBar.CALENDAR,
            navigate = viewModel::switchScreen
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_nav_create,
            route = ENavClientMain.CREATE.route,
            onClick = { viewModel.switchScreen(ENavClientMain.CREATE) }
        ),
        getItem(
            nav = ENavClientBottomBar.REPORTS,
            navigate = viewModel::switchScreen
        ),
        getItem(
            nav = ENavClientBottomBar.SETTINGS,
            navigate = viewModel::switchScreen
        ),
    )

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                items = navItems,
                spaceSize = 20.dp,
                navController = navController
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                startDestination = ENavClientBottomBar.HOME.route,
                builder = NavGraphBuilder::clientBottomBar,
            )
        }
    }
}

private fun getItem(
    nav: ENavClientBottomBar,
    navigate: (ENavClientBottomBar) -> Unit
): BottomNavigationItem {
    return BottomNavigationItem(
        icon = nav.icon,
        iconFill = nav.iconFill,
        route = nav.route,
        onClick = { navigate(nav) }
    )
}

private fun NavGraphBuilder.clientBottomBar() {
    composable(route = ENavClientBottomBar.HOME.route) {
        HomeScreen()
    }
    composable(route = ENavClientBottomBar.CALENDAR.route) {
        CalendarScreen()
    }
    composable(route = ENavClientBottomBar.REPORTS.route) {
        ReportsScreen()
    }
    composable(route = ENavClientBottomBar.SETTINGS.route) {
        SettingsScreen()
    }
}