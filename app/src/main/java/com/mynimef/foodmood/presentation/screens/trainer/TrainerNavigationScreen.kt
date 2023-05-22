package com.mynimef.foodmood.presentation.screens.trainer

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
import com.mynimef.foodmood.data.models.enums.ETrainerNavigation
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary),
                navController = navController,
                bottomNavItems,
                40.dp
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

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ETrainerNavigation.HOME.value
    ) {
        composable(ETrainerNavigation.HOME.value) { HomeScreen() }
        composable(ETrainerNavigation.CREATE.value) { CreateScreen() }
        composable(ETrainerNavigation.SETTINGS.value) { SettingsScreen() }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrainerNavigationScreenPreview() {
    FoodMoodTheme {
        TrainerNavigationScreen()
    }
}