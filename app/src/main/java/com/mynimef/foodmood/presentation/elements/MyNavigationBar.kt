package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyNavigationBar(
    modifier: Modifier,
    navController: NavHostController,
    items: List<BottomNavigationItem>,
    spaceSize: Dp
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Row(
            modifier = modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(spaceSize, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                MyNavigationItem(
                    drawableId = item.icon,
                    selected = currentRoute == item.route,
                    normalColor = Color.Black,
                    selectedColor = Color.Black
                ) {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}