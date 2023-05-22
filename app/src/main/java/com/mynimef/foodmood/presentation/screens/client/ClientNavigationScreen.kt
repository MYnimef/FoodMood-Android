package com.mynimef.foodmood.presentation.screens.client

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.mynimef.foodmood.data.models.enums.EClientNavigation
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ClientNavigationScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationItem(icon = R.drawable.ic_nav_home, iconFill = R.drawable.ic_nav_home_fill, route = "home"),
        BottomNavigationItem(icon = R.drawable.ic_nav_calendar, iconFill = R.drawable.ic_nav_calendar_fill, route = "calendar"),
        BottomNavigationItem(icon = R.drawable.ic_nav_create, iconFill = R.drawable.ic_nav_create, route = "choice"),
        BottomNavigationItem(icon = R.drawable.ic_nav_reports, iconFill = R.drawable.ic_nav_reports_fill, route = "reports"),
        BottomNavigationItem(icon = R.drawable.ic_nav_settings, iconFill = R.drawable.ic_nav_settings_fill, route = "settings"),
    )

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
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
    val navigateTo = { route: EClientNavigation ->
        navController.navigate(route.value) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = EClientNavigation.HOME.value
    ) {
        composable(EClientNavigation.HOME.value) { HomeScreen()}
        composable(EClientNavigation.CREATE.value) { CreateScreen(navigateTo = navigateTo) }
        composable(EClientNavigation.CHOICE.value) { CardsChoiceScreen(navigateTo = navigateTo) }
        composable(EClientNavigation.SETTINGS.value) { SettingsScreen(navigateTo = navigateTo) }
        composable(EClientNavigation.CALENDAR.value) { CalendarScreen() }
        composable(EClientNavigation.REPORTS.value) { ReportsScreen() }
       // composable(EClientNavigation.EMAILSEND.value) { EmailSendScreen() }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClientNavigationScreenPreview() {
    FoodMoodTheme {
        ClientNavigationScreen()
    }
}