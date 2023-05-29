package com.mynimef.foodmood.data.models.enums

import androidx.annotation.DrawableRes
import com.mynimef.foodmood.R

enum class ENavClientBottomBar(

    val route: String,

    @DrawableRes
    val icon: Int,

    @DrawableRes
    val iconFill: Int,

) {
    HOME(
        route = "home",
        icon = R.drawable.ic_nav_home,
        iconFill = R.drawable.ic_nav_home_fill,
    ),
    CALENDAR(
        route = "calendar",
        icon = R.drawable.ic_nav_calendar,
        iconFill = R.drawable.ic_nav_calendar_fill,
    ),
    REPORTS(
        route = "reports",
        icon = R.drawable.ic_nav_reports,
        iconFill = R.drawable.ic_nav_reports_fill,
    ),
    SETTINGS(
        route = "settings",
        icon = R.drawable.ic_nav_settings,
        iconFill = R.drawable.ic_nav_settings_fill,
    ),
}