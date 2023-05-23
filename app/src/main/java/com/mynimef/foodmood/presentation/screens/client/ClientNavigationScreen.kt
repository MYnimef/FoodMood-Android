package com.mynimef.foodmood.presentation.screens.client

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.mynimef.foodmood.data.models.enums.ENavigationClient
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ClientNavigationScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationItem(
            icon = R.drawable.ic_nav_home,
            iconFill = R.drawable.ic_nav_home_fill,
            route = ENavigationClient.HOME.value
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_nav_calendar,
            iconFill = R.drawable.ic_nav_calendar_fill,
            route = ENavigationClient.CALENDAR.value
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_nav_create,
            iconFill = R.drawable.ic_nav_create,
            route = ENavigationClient.CREATE.value
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_nav_reports,
            iconFill = R.drawable.ic_nav_reports_fill,
            route = ENavigationClient.REPORTS.value
        ),
        BottomNavigationItem(
            icon = R.drawable.ic_nav_settings,
            iconFill = R.drawable.ic_nav_settings_fill,
            route = ENavigationClient.SETTINGS.value
        ),
    )

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                navController = navController,
                items = bottomNavItems,
                spaceSize = 20.dp,
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            MyMavHost(
                modifier = Modifier
                    .fillMaxSize()
                ,
                navController = navController
            )
        }
    }
}

@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    val navigateTo = { route: ENavigationClient ->
        navController.navigate(route.value) {
            launchSingleTop = true
            restoreState = true
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ENavigationClient.HOME.value
    ) {
        composable(ENavigationClient.HOME.value) { HomeScreen() }
        composable(ENavigationClient.CREATE.value) { CreateScreen() }
        composable(ENavigationClient.SETTINGS.value) { SettingsScreen(navigateTo = navigateTo) }
        composable(ENavigationClient.CALENDAR.value) { CalendarScreen() }
        composable(ENavigationClient.REPORTS.value) { ReportsScreen() }
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