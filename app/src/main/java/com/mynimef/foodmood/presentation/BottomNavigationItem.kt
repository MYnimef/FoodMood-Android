package com.mynimef.foodmood.presentation

import com.mynimef.foodmood.R

sealed class BottomNavigationItem(val title: String, val icon: Int, val route: String) {
    object Home : BottomNavigationItem(
        "Home",
        R.drawable.ic_home,
        "home"
    )
    object Create: BottomNavigationItem(
        "Create",
        R.drawable.ic_create,
        "create"
    )
    object Settings: BottomNavigationItem(
        "Settings",
        R.drawable.ic_settings,
        "settings"
    )
}