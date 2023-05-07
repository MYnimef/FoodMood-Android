package com.mynimef.foodmood.presentation

import com.mynimef.foodmood.R

sealed class BottomNavigationItem(val icon: Int, val route: String) {
    object Home : BottomNavigationItem(
        R.drawable.ic_home,
        "home"
    )
    object Create: BottomNavigationItem(
        R.drawable.ic_create,
        "create"
    )
    object Settings: BottomNavigationItem(
        R.drawable.ic_settings,
        "settings"
    )
}