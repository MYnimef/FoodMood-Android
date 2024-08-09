package com.mynimef.presentation.screens.client

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.domain.models.enums.ENavClientBottomBar
import com.mynimef.domain.models.enums.ENavClientMain
import com.mynimef.presentation.R
import com.mynimef.presentation.elements.BottomNavigationItem
import com.mynimef.presentation.elements.MyNavigationBar
import com.mynimef.presentation.extensions.getIcon
import com.mynimef.presentation.extensions.getIconFill


@Composable
fun ClientBottomBarScreen() {
    val viewModel: ClientBottomBarViewModel = hiltViewModel()
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
        icon = nav.getIcon(),
        iconFill = nav.getIconFill(),
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