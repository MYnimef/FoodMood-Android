package com.mynimef.foodmood.presentation.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MyNavigationBar(
    navController: NavHostController,
    items: List<BottomNavigationItem>,
    spaceSize: Dp
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                ),
            horizontalArrangement = Arrangement.spacedBy(spaceSize, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                MyNavigationItem(
                    drawableId = item.icon,
                    drawableFillId = item.iconFill,
                    selected = currentRoute == item.route,
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