package com.mynimef.foodmood.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
    spaceSize: Dp,
    roundedCorner: Dp = 0.dp,
) = NavigationBar(
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.15f)
    ,
    containerColor = MaterialTheme.colorScheme.background,
    tonalElevation = 0.dp,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = roundedCorner, topEnd = roundedCorner)
            )
        ,
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.9f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                MyNavigationItem(
                    drawableId = item.icon,
                    drawableFillId = item.iconFill,
                    selected = currentRoute == item.route,
                    onClick = item.onClick
                )
            }
        }
    }
}