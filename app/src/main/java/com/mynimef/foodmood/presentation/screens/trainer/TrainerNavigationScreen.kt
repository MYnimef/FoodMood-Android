package com.mynimef.foodmood.presentation.screens.trainer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.mynimef.foodmood.data.models.enums.ENavigationTrainer
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@Composable
fun TrainerNavigationScreen() {
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

@Preview(showBackground = true)
@Composable
private fun TrainerNavigationScreenPreview() {
    FoodMoodTheme {
        TrainerNavigationScreen()
    }
}