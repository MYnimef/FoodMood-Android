package com.mynimef.foodmood.data.models.enums

import com.mynimef.foodmood.R

enum class ETypePeriod(val period: Int, val label: Int, ) {
    DAYS_7(period = 7, label = R.string.days_7),
    DAYS_31(period = 31, label = R.string.days_31),
}