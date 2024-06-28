package com.mynimef.foodmood.extensions

import com.mynimef.domain.models.enums.ENavClientBottomBar
import com.mynimef.data.enums.EMessage
import com.mynimef.data.enums.ETypePeriod
import com.mynimef.foodmood.R
import com.mynimef.domain.models.enums.ETypeEmotion
import com.mynimef.domain.models.enums.ETypeMeal

fun ETypeEmotion.getIcon() = when (this) {
    ETypeEmotion.VERY_BAD -> R.drawable.ic_mood_very_bad
    ETypeEmotion.BAD -> R.drawable.ic_mood_bad
    ETypeEmotion.NORMAL -> R.drawable.ic_mood_normal
    ETypeEmotion.GOOD -> R.drawable.ic_mood_good
    ETypeEmotion.GREAT -> R.drawable.ic_mood_great
}

fun ETypeMeal.getIcon() = when (this) {
    ETypeMeal.BREAKFAST -> R.drawable.ic_food_breakfast
    ETypeMeal.BRUNCH -> R.drawable.ic_food_lunch
    ETypeMeal.LUNCH -> R.drawable.ic_food_lunch
    ETypeMeal.SUPPER -> R.drawable.ic_food_supper
    ETypeMeal.DINNER -> R.drawable.ic_food_dinner
}

fun ETypeMeal.getLabel() = when (this) {
    ETypeMeal.BREAKFAST -> R.string.type_food_breakfast
    ETypeMeal.BRUNCH -> R.string.type_food_snack1
    ETypeMeal.LUNCH -> R.string.type_food_snack2
    ETypeMeal.SUPPER -> R.string.type_food_supper
    ETypeMeal.DINNER -> R.string.type_food_dinner
}

fun ENavClientBottomBar.getIcon() = when(this) {
    ENavClientBottomBar.HOME -> R.drawable.ic_nav_home
    ENavClientBottomBar.CALENDAR -> R.drawable.ic_nav_calendar
    ENavClientBottomBar.REPORTS -> R.drawable.ic_nav_reports
    ENavClientBottomBar.SETTINGS -> R.drawable.ic_nav_settings
}

fun ENavClientBottomBar.getIconFill() = when(this) {
    ENavClientBottomBar.HOME -> R.drawable.ic_nav_home_fill
    ENavClientBottomBar.CALENDAR -> R.drawable.ic_nav_calendar_fill
    ENavClientBottomBar.REPORTS -> R.drawable.ic_nav_reports_fill
    ENavClientBottomBar.SETTINGS -> R.drawable.ic_nav_settings_fill
}

fun EMessage.getText() = when(this) {
    EMessage.AUTH_FAILED -> R.string.toast_401_client
    EMessage.WRONG_EMAIL_OR_PASSWORD -> R.string.toast_401_signin
    EMessage.WRONG_INPUT -> R.string.toast_403
    EMessage.ACCOUNT_ALREADY_EXISTS -> R.string.toast_409
    EMessage.NO_CONNECTION -> R.string.toast_no_con
}

fun ETypePeriod.getNum() = when(this) {
    ETypePeriod.DAYS_7 -> 7
    ETypePeriod.DAYS_31 -> 31
}

fun ETypePeriod.getLabel() = when(this) {
    ETypePeriod.DAYS_7 -> R.string.days_7
    ETypePeriod.DAYS_31 -> R.string.days_31
}