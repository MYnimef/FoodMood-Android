package com.mynimef.foodmood.screens.trainer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mynimef.data.enums.ENavigationTrainer

@Composable
fun TrainerNavigationScreen() {
    /*
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationItem(icon = R.drawable.ic_nav_home, iconFill = R.drawable.ic_nav_home_fill, route = "home"),
        BottomNavigationItem(icon = R.drawable.ic_nav_create, iconFill = R.drawable.ic_nav_create, route = "create"),
        BottomNavigationItem(icon = R.drawable.ic_nav_settings_fill, iconFill = R.drawable.ic_nav_settings_fill, route = "settings"),
    )

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                navController = navController,
                items = bottomNavItems,
                spaceSize = 40.dp
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

     */
}

@Composable
private fun MyMavHost(
    modifier: Modifier,
    navController: NavHostController
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ENavigationTrainer.HOME.value
    ) {
        composable(ENavigationTrainer.HOME.value) { HomeScreen() }
        composable(ENavigationTrainer.CREATE.value) { CreateScreen() }
        composable(ENavigationTrainer.SETTINGS.value) { SettingsScreen() }
    }
}