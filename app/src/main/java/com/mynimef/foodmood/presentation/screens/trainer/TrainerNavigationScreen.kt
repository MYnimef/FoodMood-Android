package com.mynimef.foodmood.presentation.screens.trainer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mynimef.foodmood.R
import com.mynimef.foodmood.presentation.elements.BottomNavigationItem
import com.mynimef.foodmood.presentation.elements.MyNavigationBar
import com.mynimef.foodmood.presentation.theme.FoodMoodTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainerNavigationScreen() {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavigationItem(icon = R.drawable.ic_home, route = "home"),
        BottomNavigationItem(icon = R.drawable.ic_create, route = "create"),
        BottomNavigationItem(icon = R.drawable.ic_settings, route = "settings"),
    )

    Scaffold(
        bottomBar = {
            MyNavigationBar(
                modifier = Modifier
                    .fillMaxWidth(),
                navController = navController,
                bottomNavItems
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
        startDestination = "home"
    ) {
        composable("home") { HomeScreen() }
        composable("create") { CreateScreen() }
        composable("settings") { SettingsScreen() }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrainerNavigationScreenPreview() {
    FoodMoodTheme {
        TrainerNavigationScreen()
    }
}