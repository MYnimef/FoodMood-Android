package com.mynimef.foodmood.presentation.screens.client

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ClientNavigationScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationItem(icon = R.drawable.ic_home, route = "home"),
        BottomNavigationItem(icon = R.drawable.ic_calendar, route = "calendar"),
        BottomNavigationItem(icon = R.drawable.ic_add, route = "create"),
        BottomNavigationItem(icon = R.drawable.ic_reports, route = "reports"),
        BottomNavigationItem(icon = R.drawable.ic_settings, route = "settings"),
    )

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer),
                navController = navController,
                bottomNavItems,
                20.dp
            )
        }
    ) {
        MyMavHost(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.9f),
            navController = navController
        )
    }
}


@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    val navigateTo = { route: String ->
        navController.navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { com.mynimef.foodmood.presentation.screens.client.HomeScreen() }
        composable("create") { com.mynimef.foodmood.presentation.screens.client.CreateScreen() }
        composable("settings") { com.mynimef.foodmood.presentation.screens.client.SettingsScreen(navigateTo) }
        composable("calendar") { com.mynimef.foodmood.presentation.screens.client.CalendarScreen() }
        composable("reports") { com.mynimef.foodmood.presentation.screens.client.ReportsScreen() }
        composable("userinfo") { com.mynimef.foodmood.presentation.screens.client.UserInfoScreen() }
        composable("prefsettings") { com.mynimef.foodmood.presentation.screens.client.PrefSettingsScreen(navigateTo)}
        composable("notifications") { com.mynimef.foodmood.presentation.screens.client.NotificationsScreen() }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClientNavigationScreenPreview() {
    FoodMoodTheme {
        ClientNavigationScreen()
    }
}